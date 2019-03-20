package com.test.grainchainapplication.dagger

import com.test.grainchainapplication.modules.SearchFragment
import dagger.Subcomponent
import dagger.android.AndroidInjector

/**
 * Created by Luis Vargas on 3/19/19.
 */

@Subcomponent
interface SearchFragmentSubComponent : AndroidInjector<SearchFragment> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<SearchFragment>()
}