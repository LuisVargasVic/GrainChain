package com.test.grainchainapplication.navigator

import androidx.appcompat.app.AppCompatActivity

/**
 * Created by Luis Vargas on 3/19/19.
 */
interface Navigator {
    fun navigateToLoginScreen(activity: AppCompatActivity)
    fun navigateToTabScreen(activity: AppCompatActivity)
}