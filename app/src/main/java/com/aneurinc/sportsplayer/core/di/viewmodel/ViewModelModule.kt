
package com.aneurinc.sportsplayer.core.di.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.aneurinc.sportsplayer.features.athletes.AthletesViewModel
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
    abstract fun bindsMoviesViewModel(viewModel: AthletesViewModel): ViewModel

}