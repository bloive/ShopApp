package com.example.shopappp.ui.home

import android.Manifest
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.navigation.NavHost
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.shopappp.BaseFragment
import com.example.shopappp.R
import com.example.shopappp.databinding.HomeFragmentBinding

class HomeFragment : BaseFragment<HomeFragmentBinding>(HomeFragmentBinding::inflate) {

    private val viewModel: HomeViewModel by viewModels()

    override fun start(inflater: LayoutInflater, container: ViewGroup?) {
        initBottomNav()
        initDrawerMenu()
        requestPermission(requestPermissionsResult)
    }

    private fun initBottomNav() {
        val navController =
            childFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        binding!!.navView.setupWithNavController(navController.navController)
    }

    private fun initDrawerMenu() {
        TODO("not yet implemented")
    }

    private val requestPermissionsResult =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
            if (it[Manifest.permission.CAMERA] == true && it[Manifest.permission.WRITE_EXTERNAL_STORAGE] == true) {
                openCamera()
            }
        }

    fun requestPermission(request: ActivityResultLauncher<Array<String>>) {
        request.launch(
            arrayOf(
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
        )
    }
}