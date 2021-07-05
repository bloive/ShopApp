package com.example.shopappp.user_data

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPreference @Inject constructor(@ApplicationContext context: Context) {
    companion object {
        const val HAS_SESSION = "HAS_SESSION"
    }

    private val sharedPreference = context.getSharedPreferences("user", Context.MODE_PRIVATE)

    fun saveUserSession(session: Boolean) {
        sharedPreference.edit().putBoolean(HAS_SESSION, session).apply()
    }

    fun hasSession() = sharedPreference.getBoolean(HAS_SESSION, false)
}