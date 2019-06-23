package com.nsc9012.sportsplayer.features.athletes

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.nsc9012.sportsplayer.R
import com.nsc9012.sportsplayer.core.extension.inflate
import com.nsc9012.sportsplayer.core.extension.loadFromUrl
import com.nsc9012.sportsplayer.core.navigation.Navigator
import kotlinx.android.synthetic.main.adapter_row_athlete.view.*
import javax.inject.Inject
import kotlin.properties.Delegates

class AthletesAdapter
@Inject constructor() : RecyclerView.Adapter<AthletesAdapter.ViewHolder>() {

    internal var collection: List<AthleteView> by Delegates.observable(emptyList()) {
            _, _, _ -> notifyDataSetChanged()
    }

    internal var clickListener: (AthleteView, Navigator.Extras) -> Unit = { _, _ -> }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(parent.inflate(R.layout.adapter_row_athlete))

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) =
        viewHolder.bind(collection[position], clickListener)

    override fun getItemCount() = collection.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(athleteView: AthleteView, clickListener: (AthleteView, Navigator.Extras) -> Unit) {
            itemView.athletePicture.loadFromUrl(athleteView.pictureUrl)
            itemView.setOnClickListener { clickListener(athleteView, Navigator.Extras(itemView.athletePicture)) }
        }
    }
}
