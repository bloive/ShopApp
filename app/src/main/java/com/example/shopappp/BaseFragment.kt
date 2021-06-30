package com.example.shopappp

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.shopappp.ui.MainActivity

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

abstract class BaseFragment<VBinding : ViewBinding>(private val inflate: Inflate<VBinding>) :
    Fragment() {

    private var binding: VBinding? = null
    protected abstract fun getViewBinding(): VBinding
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
}