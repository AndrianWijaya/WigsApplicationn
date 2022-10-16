package com.iglo.service.auth

import android.content.Context
import android.preference.PreferenceManager
import com.iglo.common.entity.Login

class AuthDatabaseServicePref(val context: Context) : AuthDatabaseService {

    val preferences = PreferenceManager.getDefaultSharedPreferences(context)

    override fun login(login: Login) {
        preferences.edit().putBoolean(USER_LOGGED, true).putString(USER, login.user).apply()
    }

    override fun isUserLoggedIn(): Boolean {
       return preferences.getBoolean(USER_LOGGED, false)
     }

    override fun getUser(): String {
        return preferences.getString(USER, "")?: ""
    }

    companion object {
        const val USER_LOGGED = "USER_LOGGED"
        const val USER = "USER"
    }
}