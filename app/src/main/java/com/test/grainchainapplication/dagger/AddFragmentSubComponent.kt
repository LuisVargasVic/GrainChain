package com.test.grainchainapplication.dagger

import com.test.grainchainapplication.modules.AddFragment
import dagger.Subcomponent
import dagger.android.AndroidInjector

/**
 * Created by Luis Vargas on 3/20/19.
 */

@Subcomponent
interface AddFragmentSubComponent : AndroidInjector<AddFragment> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<AddFragment>()
}