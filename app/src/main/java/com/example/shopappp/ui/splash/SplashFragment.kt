package com.example.shopappp.ui.splash

import android.animation.Animator
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.shopappp.BaseFragment
import com.example.shopappp.R
import com.example.shopappp.databinding.SplashFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BaseFragment<SplashFragmentBinding>(SplashFragmentBinding::inflate) {

    private val viewModel: SplashViewModel by viewModels()

    override fun start(inflater: LayoutInflater, container: ViewGroup?) {
        binding!!.animationView.playAnimation()
        binding!!.animationView.addAnimatorListener(object : Animator.AnimatorListener{
            override fun onAnimationStart(animation: Animator?) {
                TODO("Not yet implemented")
            }

            override fun onAnimationEnd(animation: Animator?) {
                startFragment()
            }

            override fun onAnimationCancel(animation: Animator?) {
                TODO("Not yet implemented")
            }

            override fun onAnimationRepeat(animation: Animator?) {
                TODO("Not yet implemented")
            }

        })
    }

    private fun startFragment() {
        with(findNavController()) {
            if (viewModel.isAuthorized()) {
                navigate(R.id.action_splashFragment_to_homeFragment)
            } else {
                navigate(R.id.action_splashFragment_to_loginFragment)
                d("splash", "SPLASH")
            }
        }
    }
}