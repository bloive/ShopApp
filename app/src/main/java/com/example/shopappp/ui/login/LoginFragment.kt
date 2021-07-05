package com.example.shopappp.ui.login

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import com.example.shopappp.BaseFragment
import com.example.shopappp.R
import com.example.shopappp.databinding.LoginFragmentBinding
import com.example.shopappp.extensions.isEmail
import com.example.shopappp.extensions.setMultiColors
import com.example.shopappp.network.Resource
import com.example.shopappp.network.Resource.Companion.error
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Response.error
import kotlin.math.sign

@AndroidEntryPoint
class LoginFragment : BaseFragment<LoginFragmentBinding>(LoginFragmentBinding::inflate) {

    private var _binding: LoginFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun start(inflater: LayoutInflater, container: ViewGroup?) {
        binding!!.tvSignUp.setMultiColors(
            listOf(
                "New User?", "Sign up", "here"
            ), listOf(R.color.text, R.color.salmon_pink, R.color.text)
        )
        setListeners()
        observes()
    }

    private fun setListeners() {
        with(binding) {
            btnSignIn.setOnClickListener {
                signIn()
            }
            tvSignUp.setOnClickListener {

            }
            tvForgotPassword.setOnClickListener {

            }
            textInputEmail.doOnTextChanged { text, start, before, count ->
                validateEmail(text.toString())
            }
        }
    }

    private fun validateEmail(email: String) {
        binding.tfEmail.isEndIconVisible = email.isEmail()
    }

    private fun signIn() {
        val email = binding.textInputEmail.text.toString()
        val password = binding.textInputPassword.text.toString()
        if (email.isNotBlank() && password.isNotBlank()) {
            if (email.isEmail()) {
                viewModel.login(email, password)
            } else {
//                showErrorDialog()
            }
        } else {
//            showErrorDialog()
        }
    }

    private fun observes() {
        viewModel.responseLiveData.observe(viewLifecycleOwner, {
            when(it.status) {
                Resource.Status.SUCCESS -> {}
                Resource.Status.ERROR -> {}
                Resource.Status.LOADING -> {}
            }
        })
    }
}