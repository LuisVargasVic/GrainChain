package com.test.grainchainapplication.navigator

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.test.grainchainapplication.modules.LoginActivity
import com.test.grainchainapplication.modules.TabActivity
import javax.inject.Inject

/**
 * Created by Luis Vargas on 3/19/19.
 */

class NavigatorImpl @Inject constructor(
    private val mContext: Context
) : Navigator {

    override fun navigateToLoginScreen(activity: AppCompatActivity) {
        val intent = Intent(mContext, LoginActivity::class.java)
        mContext.startActivity(intent)
        activity.finish()
    }

    override fun navigateToTabScreen(activity: AppCompatActivity) {
        val intent = Intent(mContext, TabActivity::class.java)
        mContext.startActivity(intent)
        activity.finish()
    }
}