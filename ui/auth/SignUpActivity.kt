package com.example.kotlin_mvvm.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.kotlin_mvvm.R
import com.example.kotlin_mvvm.databinding.ActivitySignupBinding
import com.example.kotlin_mvvm.ui.home.HomeActivity
import com.example.kotlin_mvvm.utill.ApiException
import com.example.kotlin_mvvm.utill.NoInternetException
import com.example.kotlin_mvvm.utill.toast
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class SignUpActivity : AppCompatActivity(), KodeinAware {

    override val kodein by kodein()

    private val factory : AuthViewModelFactory by instance()

    private lateinit var binding: ActivitySignupBinding

    private lateinit var viewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_signup)
        viewModel = ViewModelProvider(this,factory).get(AuthViewModel::class.java)


        viewModel.getLoggedInUser().observe(this, Observer { user ->
            if(user != null) {
                Intent(this, HomeActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }
        })

        binding.buttonSignUp.setOnClickListener {
            userSignUp()
        }

        binding.textViewSignIn.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
    private fun userSignUp() {
        val name = binding.editTextName.text.toString().trim()
        val email = binding.editTextEmail.text.toString().trim()
        val password = binding.editTextPassword.text.toString().trim()
        val confirm = binding.editTextPasswordConfirm.toString().trim()

        //@todo add input validations
        if(confirm === password) {
            lifecycleScope.launch {
                val authResponse = viewModel.userSignUp(name,email,password)
                try {
                    if(authResponse.user != null) {
                        viewModel.saveLoggedInUser(authResponse.user)
                    } else {
                        toast(authResponse.message!!)
                    }
                } catch (e: ApiException) {
                    e.printStackTrace()
                } catch (e: NoInternetException) {
                    e.printStackTrace()
                }
            }
        } else {
            toast("Password not equal Confirm")
            return
        }



    }
}