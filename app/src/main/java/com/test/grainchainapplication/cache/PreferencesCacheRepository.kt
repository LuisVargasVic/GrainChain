package com.test.grainchainapplication.cache

/**
 * Created by Luis Vargas on 3/19/19.
 */
interface PreferencesCacheRepository {

    // Session preferences
    fun getUsername(): String?
    fun setUsername(username: String)
}