package com.example.ttt.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.ttt.R
import com.example.ttt.databinding.ActivitySignupBinding
import com.example.ttt.model.SignupViewModel


class SignupActivity : AppCompatActivity() {

    private lateinit var binding:ActivitySignupBinding
    private lateinit var viewModel:SignupViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_signup)
        viewModel = ViewModelProvider(this).get(SignupViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }
}
