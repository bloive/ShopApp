package com.example.shopappp.ui.register

import android.app.Dialog
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.shopappp.BaseFragment
import com.example.shopappp.R
import com.example.shopappp.databinding.ErrorDialogBinding
import com.example.shopappp.databinding.LoginFragmentBinding
import com.example.shopappp.extensions.hide
import com.example.shopappp.extensions.showIf
import com.example.shopappp.extensions.start
import com.example.shopappp.network.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : BaseFragment<LoginFragmentBinding>(LoginFragmentBinding::inflate) {

    private val viewModel: RegisterViewModel by viewModels()

    override fun start(inflater: LayoutInflater, container: ViewGroup?) {
        init()
        observes()
    }

    private fun init() {
        TODO("frustrated")
    }

    private fun observes() {
        viewModel.registerResponseLiveData.observe(viewLifecycleOwner, {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    binding!!.progress.hide()

                    val email = binding!!.textInputEmail.text.toString()
                    val password = binding!!.textInputPassword.text.toString()
                    findNavController().navigate(
                        R.id.action_registerFragment_to_homeFragment,
                        bundleOf("email" to email, "password" to password)
                    )
                }
                Resource.Status.ERROR -> {
                    binding!!.progress.hide()
                    val dialog = Dialog(requireContext())
                    val dialogBinding = ErrorDialogBinding.inflate(layoutInflater)
                    dialog.start(dialogBinding.root)
                    dialogBinding.tvDescription.text = it.message
                    dialogBinding.btnClose.setOnClickListener {
                        dialog.cancel()
                    }
                    dialog.show()
                }
                Resource.Status.LOADING -> binding!!.progress.showIf(it.loading)
            }
        })
    }
}