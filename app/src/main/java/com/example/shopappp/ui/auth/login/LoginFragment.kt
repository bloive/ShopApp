package com.example.shopappp.ui.auth.login

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.shopappp.BaseFragment
import com.example.shopappp.R
import com.example.shopappp.databinding.ErrorDialogBinding
import com.example.shopappp.databinding.LoginFragmentBinding
import com.example.shopappp.extensions.*
import com.example.shopappp.network.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<LoginFragmentBinding>(LoginFragmentBinding::inflate) {

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun start(inflater: LayoutInflater, container: ViewGroup?) {
        binding!!.tfEmail.isEndIconVisible = false
        binding!!.tvSignUp.setMultiColors(
            listOf(
                "New User?", "Sign up", "here"
            ), listOf(R.color.text, R.color.salmon_pink, R.color.text)
        )
        setListeners()
        observes()
    }

    private fun setListeners() {
        with(binding!!) {
            btnSignIn.setOnClickListener {
                signIn()
            }
            tvSignUp.setOnClickListener {
                toSignUp()
            }
            tvForgotPassword.setOnClickListener {

            }
            textInputEmail.doOnTextChanged { text, start, before, count ->
                validateEmail(text.toString())
            }
        }
    }

    private fun validateEmail(email: String) {
        binding!!.tfEmail.isEndIconVisible = email.isEmail()
    }

    private fun signIn() {
        val email = binding!!.textInputEmail.text.toString()
        val password = binding!!.textInputPassword.text.toString()
        if (email.isNotBlank() && password.isNotBlank()) {
            if (email.isEmail()) {
                viewModel.login(email, password)
            } else {
                showErrorDialog("Email not valid")
            }
        } else {
            showErrorDialog("Email or password not valid")
        }
    }

    private fun observes() {
        viewModel.responseLiveData.observe(viewLifecycleOwner, {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                    binding!!.progress.hide()
                }
                Resource.Status.ERROR -> {
                    binding!!.progress.hide()
                    it.message?.let { it1 -> showErrorDialog(it1) }
                }
                Resource.Status.LOADING -> {
                    binding!!.progress.showIf(it.loading)
                }
            }
        })

        viewModel.completeProfileLiveData.observe(viewLifecycleOwner, {
            binding!!.progress.hideIf(!it.loading)
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    if (it.data!!.profileCompleted)
                        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                    else
                        findNavController().navigate(R.id.action_loginFragment_to_completeFragment)
                }
                Resource.Status.ERROR -> {
                    it.message?.let { it1 -> showErrorDialog(getString(R.string.error), it1) }
                }
                Resource.Status.LOADING -> {
                }
            }
        })
    }

    private fun showErrorDialog(message: String) {
        val dialog = Dialog(requireContext())
        val dialogBinding = ErrorDialogBinding.inflate(layoutInflater)
        dialog.start(dialogBinding.root)
        dialogBinding.tvDescription.text = message
        dialogBinding.btnClose.setOnClickListener {
            dialog.cancel()
        }
        dialog.show()
    }

    private fun toSignUp() {
        findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
    }
}