package com.example.shopappp

import android.Manifest
import android.app.Dialog
import android.app.Instrumentation
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.shopappp.ui.MainActivity

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

abstract class BaseFragment<VBinding : ViewBinding>(val inflate: Inflate<VBinding>) :
    Fragment() {

    var binding: VBinding? = null
//    protected abstract fun getViewBinding(): VBinding
    private var mainActivity: MainActivity? = null

    override fun onAttach(context: Context) {
        if (context is MainActivity)
            mainActivity = context
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
        mainActivity = null
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (binding == null) {
            binding = inflate.invoke(inflater, container, false)
            start(inflater, container)
        }
        return binding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    abstract fun start(
        inflater: LayoutInflater,
        container: ViewGroup?
    )

    fun showErrorDialog(title:String, description: String) {
        val dialog = Dialog(requireContext())
        TODO("Not yet implemented")
        dialog.show()
    }

    fun request() {

        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
            if (it[Manifest.permission.CAMERA] == true && it[Manifest.permission.WRITE_EXTERNAL_STORAGE] == true) {
                openCamera()
            }
        }.launch(arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
        ))
    }

    fun openCamera() {

    }
}