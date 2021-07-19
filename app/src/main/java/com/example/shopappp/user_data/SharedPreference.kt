package com.example.shopappp.user_data

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPreference @Inject constructor(@ApplicationContext context: Context) {
    companion object {
        const val HAS_SESSION = "HAS_SESSION"
        const val TOKEN = "TOKEN"
        const val USER_ID = "USER_ID"
    }

    private val sharedPreference = context.getSharedPreferences("user", Context.MODE_PRIVATE)

    fun saveUserSession(session: Boolean) {
        sharedPreference.edit().putBoolean(HAS_SESSION, session).apply()
    }

    fun hasSession() = sharedPreference.getBoolean(HAS_SESSION, false)

    fun saveToken(token: String) {
        sharedPreference.edit().putString(TOKEN, token).apply()
    }

    fun token() = sharedPreference.getString(TOKEN, "")

    fun saveUserId(userId: Int) {
        sharedPreference.edit().putInt(USER_ID, userId).apply()
    }

    fun userId() =sharedPreference.getInt(USER_ID, 0)
}