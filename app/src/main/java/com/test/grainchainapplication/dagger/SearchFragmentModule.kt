package com.test.grainchainapplication.dagger

import androidx.fragment.app.Fragment
import com.test.grainchainapplication.modules.SearchFragment
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.android.support.FragmentKey
import dagger.multibindings.IntoMap

/**
 * Created by Luis Vargas on 3/19/19.
 */

@Module(subcomponents = [SearchFragmentSubComponent::class])
abstract class SearchFragmentModule {
    @Binds
    @IntoMap
    @FragmentKey(SearchFragment::class)
    abstract fun bindSearchFragmentInjectorFactory(builder: SearchFragmentSubComponent.Builder): AndroidInjector.Factory<out Fragment>
}