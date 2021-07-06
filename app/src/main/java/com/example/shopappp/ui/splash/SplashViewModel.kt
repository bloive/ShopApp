package com.example.shopappp.ui.splash

import androidx.lifecycle.ViewModel
import com.example.shopappp.user_data.SharedPreference
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val sharedPreference: SharedPreference): ViewModel() {
    fun isAuthorized() = sharedPreference.hasSession()
}

