package com.example.shopappp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.shopappp.BaseFragment
import com.example.shopappp.databinding.HomeFragmentBinding

class HomeFragment : BaseFragment<HomeFragmentBinding>(HomeFragmentBinding::inflate) {

    private val viewModel: HomeViewModel by viewModels()

    override fun start(inflater: LayoutInflater, container: ViewGroup?) {
        initBottomNav()
        initDrawerMenu()
    }

    private fun initBottomNav() {

    }

    private fun initDrawerMenu() {
        TODO("not yet implemented")
    }
}