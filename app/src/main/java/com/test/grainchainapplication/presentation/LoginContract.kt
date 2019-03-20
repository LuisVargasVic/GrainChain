package com.test.grainchainapplication.presentation

import com.test.grainchainapplication.models.LoginRequest
import com.test.grainchainapplication.remote.NetworkApiService

/**
 * Created by Luis Vargas on 3/19/19.
 */

interface LoginContract {
    interface View : BaseView<Presenter> {
        fun onLoginSuccess()
    }

    interface Presenter : BasePresenter {
        fun login(
            params: LoginRequest,
            networkApiService: NetworkApiService
        )
    }
}