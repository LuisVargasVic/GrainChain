package com.test.grainchainapplication.presentation

import com.test.grainchainapplication.cache.PreferencesCacheRepository
import com.test.grainchainapplication.models.LoginRequest
import com.test.grainchainapplication.models.LoginResponse
import com.test.grainchainapplication.remote.NetworkApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by Luis Vargas on 3/19/19.
 */

class LoginPresenter @Inject constructor(
    private val view: LoginContract.View,
    private val preferences: PreferencesCacheRepository) : LoginContract.Presenter {

    init {
        view.setPresenter(this)
    }

    override fun login(
        params: LoginRequest,
        networkApiService: NetworkApiService
    ) {
        view.showLoading()
        val request : Call<LoginResponse> = networkApiService.login(params)
        request.enqueue(object : Callback<LoginResponse> {

            override fun onResponse(call: Call<LoginResponse>?, response: Response<LoginResponse>?) {
                view.hideLoading()

                if (response?.body() != null ) {
                    if (response.code() == 200) {
                        preferences.setUsername(response.body()!!.body.auth.user.name)
                        view.onLoginSuccess()
                    } else {
                        view.showMessage("Error")
                    }

                } else {
                    view.showMessage("Error")
                }
            }

            override fun onFailure(call: Call<LoginResponse>?, t: Throwable?) {
                view.hideLoading()
                t?.localizedMessage?.let { view.showMessage(it) }
                call?.cancel()
            }
        })
    }
}