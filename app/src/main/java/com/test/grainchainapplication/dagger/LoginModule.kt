package com.test.grainchainapplication.dagger

import com.test.grainchainapplication.cache.PreferencesCacheRepository
import com.test.grainchainapplication.modules.LoginActivity
import com.test.grainchainapplication.presentation.LoginContract
import com.test.grainchainapplication.presentation.LoginPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by Luis Vargas on 3/19/19.
 */

@Module
class LoginModule {

    @PerActivity
    @Provides
    fun bindLoginView(activity: LoginActivity): LoginContract.View {
        return activity
    }

    @PerActivity
    @Provides
    fun provideLoginPresenter(
        loginView: LoginContract.View,
        preferences: PreferencesCacheRepository): LoginContract.Presenter {
        return LoginPresenter(loginView, preferences)
    }
}