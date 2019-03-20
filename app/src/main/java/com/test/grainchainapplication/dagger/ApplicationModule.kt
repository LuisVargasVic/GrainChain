package com.test.grainchainapplication.dagger

import android.app.Application
import android.content.Context
import android.content.res.Resources
import android.preference.PreferenceManager
import com.test.grainchainapplication.cache.PreferenceCacheImpl
import com.test.grainchainapplication.cache.PreferencesCacheRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Luis Vargas on 3/19/19.
 */

@Module
class ApplicationModule {

    @Singleton
    @Provides
    fun providesApplicationContext(application: Application): Context {
        return application
    }

    @Singleton
    @Provides
    fun providesResources(application: Application): Resources {
        return application.resources
    }

    @Singleton
    @Provides
    fun providePreferencesCache(context: Context): PreferencesCacheRepository {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        return PreferenceCacheImpl(preferences)
    }
}