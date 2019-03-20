package com.test.grainchainapplication.dagger

import com.test.grainchainapplication.modules.LoginActivity
import com.test.grainchainapplication.modules.TabActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Luis Vargas on 3/19/19.
 */

@Module
abstract class ActivityBindingModule {

    @PerActivity
    @ContributesAndroidInjector(modules = [LoginModule::class, NavigatorModule::class])
    abstract fun bindLoginActivity(): LoginActivity

    @PerActivity
    @ContributesAndroidInjector(modules = [TabModule::class, SearchFragmentModule::class, AddFragmentModule::class])
    abstract fun bindTabActivity(): TabActivity
}