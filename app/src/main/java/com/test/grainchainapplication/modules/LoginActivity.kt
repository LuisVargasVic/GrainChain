package com.test.grainchainapplication.modules

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Window
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.test.grainchainapplication.BuildConfig
import com.test.grainchainapplication.R
import com.test.grainchainapplication.databinding.ActivityLoginBinding
import com.test.grainchainapplication.databinding.ViewProgressDialogBinding
import com.test.grainchainapplication.models.LoginRequest
import com.test.grainchainapplication.navigator.Navigator
import com.test.grainchainapplication.navigator.NavigatorImpl
import com.test.grainchainapplication.presentation.LoginContract
import com.test.grainchainapplication.remote.NetworkApiService
import com.test.grainchainapplication.remote.NetworkApiServiceFactory
import dagger.android.AndroidInjection
import javax.inject.Inject

class LoginActivity : AppCompatActivity(), LoginContract.View {

    @Inject
    lateinit var mPresenter: LoginContract.Presenter

    private lateinit var navigator: Navigator
    private lateinit var progressDialog: AlertDialog
    private lateinit var networkApiService: NetworkApiService
    private lateinit var mBinding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        networkApiService = NetworkApiServiceFactory(this).makeApiService(BuildConfig.SERVER_URL)
        progressDialog = setUpProgressDialog()
        navigator = NavigatorImpl(this)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        setUpUI()
    }

    private fun setUpUI(){
        mBinding.activityLoginBt.setOnClickListener {
            performLogin()
        }
    }

    private fun performLogin(){
        /*mPresenter.login(LoginRequest("harvx_190878", "supersecretpassword"), networkApiService)
        return*/
        if (isValidForm()) {
            mPresenter.login(LoginRequest(
                mBinding.activityLoginEtUser.text.toString(),
                mBinding.activityLoginEtPassword.text.toString()
            ), networkApiService)
        }
    }

    private fun isValidForm(): Boolean {
        return when {
            mBinding.activityLoginEtUser.text.toString() == "" -> {
                showMessage("Username is required")
                false
            }
            mBinding.activityLoginEtPassword.text.toString() == "" -> {
                showMessage("Password is required")
                false
            }
            else -> true
        }
    }

    override fun onLoginSuccess() {
        navigator.navigateToTabScreen(this)
    }

    override fun setPresenter(presenter: LoginContract.Presenter) {
        mPresenter = presenter
    }

    override fun showLoading() {
        progressDialog.show()
    }

    override fun hideLoading() {
        progressDialog.dismiss()
    }

    override fun showMessage(message: String) {
        createDialogMessage(message).show()
    }

    private fun setUpProgressDialog(): AlertDialog {
        val bindingAdapter = DataBindingUtil.inflate<ViewProgressDialogBinding>(LayoutInflater.from(this),
            R.layout.view_progress_dialog, null, false)
        val builder = AlertDialog.Builder(this)
        builder.setCancelable(false)
        builder.setView(bindingAdapter.root)

        return builder.create()
    }

    private fun createDialogMessage(message: String): AlertDialog {
        val alertDialog = AlertDialog.Builder(this).create()
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        alertDialog.setMessage(message)
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK") { _, _ ->
            // Dismiss dialog
            alertDialog.dismiss()
        }
        return alertDialog
    }
}
