package com.test.grainchainapplication.cache

import android.content.SharedPreferences
import javax.inject.Inject

/**
 * Created by Luis Vargas on 3/19/19.
 */

class PreferenceCacheImpl @Inject constructor(
    private var sharedPreferences : SharedPreferences
) : PreferencesCacheRepository {

    private val preferenceKeyUsername = "pref.username"


    override fun setUsername(username: String) {
        sharedPreferences
            .edit()
            .putString(preferenceKeyUsername, username)
            .apply()
    }

    override fun getUsername(): String? {
        return sharedPreferences.getString(preferenceKeyUsername, "")
    }
}