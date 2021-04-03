package com.example.ankitjadavpracticaltest.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.ankitjadavpracticaltest.R
import com.example.ankitjadavpracticaltest.data.entity.User
import com.example.ankitjadavpracticaltest.databinding.ActivityLoginBinding
import com.example.ankitjadavpracticaltest.ui.exchangerate.ExchangeRateListActivity
import org.koin.android.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    private val loginViewModel: LoginViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.login = loginViewModel
        binding.lifecycleOwner=this

        loginViewModel.getUser().observe(this, Observer {
            validateUser(it)
        })
    }

    private fun validateUser(it: User) {
        if (it.username.isEmpty()) {
            binding.edtUsername.error = "Username cannot be empty"
        } else if (!loginViewModel.isUsernameValid()) {
            binding.edtUsername.error = "Please enter valid username"
        } else if (it.password.isEmpty()) {
            binding.edtPwd.error = "Password cannot be empty"
        } else if (loginViewModel.isValidCredentials()) {
            Toast.makeText(this, "User Login Successfully", Toast.LENGTH_SHORT).show()
            redirectToExchangeRateListActivity()
        } else {
            Toast.makeText(this, "Invalid Credentials", Toast.LENGTH_SHORT).show()
        }
    }

    fun redirectToExchangeRateListActivity() {
        startActivity(Intent(this, ExchangeRateListActivity::class.java))
        finish()
    }

}