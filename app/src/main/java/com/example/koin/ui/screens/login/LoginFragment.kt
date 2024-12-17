package com.example.koin.ui.screens.login

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.koin.R
import com.example.koin.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : BaseFragment<LoginViewModel>(LoginViewModel::class.java) {
    private val loginViewModel: LoginViewModel by viewModel()

    override fun setupViews() {
        val emailField = view?.findViewById<EditText>(R.id.editTextEmail)
        val passwordField = view?.findViewById<EditText>(R.id.editTextPassword)
        val loginButton = view?.findViewById<Button>(R.id.buttonLogin)

        loginButton?.setOnClickListener {
            val email = emailField?.text.toString()
            val password = passwordField?.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                loginViewModel.login(email, password)
            } else {
                Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun setupObservers() {
        loginViewModel.loginState.observe(viewLifecycleOwner) { response ->
            if (response.status) {
                findNavController().navigate(R.id.action_login_to_home)
            } else {
                Toast.makeText(requireContext(), response.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
