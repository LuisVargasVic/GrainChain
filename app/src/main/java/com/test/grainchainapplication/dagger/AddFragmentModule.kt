package com.test.grainchainapplication.dagger

import androidx.fragment.app.Fragment
import com.test.grainchainapplication.modules.AddFragment
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.android.support.FragmentKey
import dagger.multibindings.IntoMap

/**
 * Created by Luis Vargas on 3/20/19.
 */

@Module(subcomponents = [AddFragmentSubComponent::class])
abstract class AddFragmentModule {
    @Binds
    @IntoMap
    @FragmentKey(AddFragment::class)
    abstract fun bindAddFragmentInjectorFactory(builder: AddFragmentSubComponent.Builder): AndroidInjector.Factory<out Fragment>
}