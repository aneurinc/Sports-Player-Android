
package com.nsc9012.sportsplayer.core.di.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.nsc9012.sportsplayer.features.athletes.AthleteDetailsViewModel
import com.nsc9012.sportsplayer.features.athletes.AthletesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(AthletesViewModel::class)
    abstract fun bindsAthletesViewModel(viewModel: AthletesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AthleteDetailsViewModel::class)
    abstract fun bindsAthleteDetailsViewModel(viewModel: AthleteDetailsViewModel): ViewModel

}