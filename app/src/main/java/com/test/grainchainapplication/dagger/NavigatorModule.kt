package com.test.grainchainapplication.dagger

import android.content.Context
import com.test.grainchainapplication.navigator.Navigator
import com.test.grainchainapplication.navigator.NavigatorImpl
import dagger.Module
import dagger.Provides

/**
 * Created by Luis Vargas on 3/19/19.
 */

@Module
open class NavigatorModule {

    @PerActivity
    @Provides
    fun provideNavigator(
        context: Context): Navigator {
        return NavigatorImpl(context)
    }
}