package com.miw.mymovie.model.data.storage.local

import android.content.Context
import android.content.SharedPreferences

class Settings(context: Context) {
    companion object {
        const val PREFS_NAME = "App"
        const val LOGIN = "login"
        const val DEFAULT_LOGIN = ""
    }

    private val preferences: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    var login: String?
    get() = preferences.getString(LOGIN, DEFAULT_LOGIN)
    set(username) {
        with(preferences.edit()) {
            putString(LOGIN, username)
            apply()
        }
    }

    fun clearAll() = preferences.edit().clear().apply()
}