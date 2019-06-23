package com.nsc9012.sportsplayer.features.athletes

import android.net.Uri
import android.os.Bundle
import android.view.View
import com.nsc9012.sportsplayer.core.platform.BaseFragment
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import kotlinx.android.synthetic.main.fragment_video_player.*
import android.view.WindowManager
import com.nsc9012.sportsplayer.R
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.source.TrackGroupArray
import com.google.android.exoplayer2.trackselection.TrackSelectionArray
import com.google.android.exoplayer2.util.Util.getUserAgent


class AthleteVideoPlayerFragment : BaseFragment() {

    companion object {
        private const val PARAM_VIDEO_URL = "param_video_url"

        fun forVideo(videoUrl: String): AthleteVideoPlayerFragment {
            val athleteVideoFragment = AthleteVideoPlayerFragment()
            val arguments = Bundle()
            arguments.putString(PARAM_VIDEO_URL, videoUrl)
            athleteVideoFragment.arguments = arguments
            return athleteVideoFragment
        }
    }

    private lateinit var player: SimpleExoPlayer

    private var isPlaying = false

    private val playerEventListener = object : Player.EventListener {

        override fun onPlaybackParametersChanged(playbackParameters: PlaybackParameters?) {}
        override fun onSeekProcessed() {}
        override fun onPositionDiscontinuity(reason: Int) {}
        override fun onRepeatModeChanged(repeatMode: Int) {}
        override fun onShuffleModeEnabledChanged(shuffleModeEnabled: Boolean) {}
        override fun onTimelineChanged(timeline: Timeline?, manifest: Any?, reason: Int) {}
        override fun onLoadingChanged(isLoading: Boolean) {}
        override fun onPlayerError(error: ExoPlaybackException?) {}
        override fun onTracksChanged(trackGroups: TrackGroupArray?, trackSelections: TrackSelectionArray?) {}

        override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
            isPlaying = playWhenReady
            if (playWhenReady) {
                activity!!.window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
            } else {
                activity!!.window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
            }
            if (playbackState == Player.STATE_BUFFERING) {
                progressBar.show()
            } else {
                progressBar.hide()
            }
        }


    }

    override fun layoutId() = R.layout.fragment_video_player

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initExoPlayer()
        playTrack()
    }

    private fun initExoPlayer() {

        val trackSelector = DefaultTrackSelector()
        val loadControl = DefaultLoadControl()
        val renderersFactory = DefaultRenderersFactory(context)

        player = ExoPlayerFactory.newSimpleInstance(
            renderersFactory, trackSelector, loadControl
        )

        playerView.player = player

        player.addListener(playerEventListener)

    }

    private fun playTrack() {

        val userAgent = getUserAgent(context, getString(R.string.app_name))
        val trackUrl = arguments!!.getString(PARAM_VIDEO_URL)

        val mediaSource = ExtractorMediaSource
            .Factory(DefaultDataSourceFactory(context, userAgent))
            .setExtractorsFactory(DefaultExtractorsFactory())
            .createMediaSource(Uri.parse(trackUrl))

        player.prepare(mediaSource)
        player.playWhenReady = true

    }

    private fun stopPlayer() {
        player.removeListener(playerEventListener)
        player.stop()
        player.release()
    }

    override fun onDetach() {
        super.onDetach()
        stopPlayer()
    }


}