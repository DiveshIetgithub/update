package com.example.ttt.activity


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.ttt.R
import com.example.ttt.databinding.ActivitySharedPrefsBinding
import com.example.ttt.factory.SharedPrefRepositoryViewModelFactory
import com.example.ttt.model.SharedPrefRepositoryViewModel
import com.example.ttt.repository.SharedPrefRepository


class SharedPrefsActivity : AppCompatActivity() {
    lateinit var binding: ActivitySharedPrefsBinding
    lateinit var factory: SharedPrefRepositoryViewModelFactory
    lateinit var viewModel:SharedPrefRepositoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_shared_prefs)
        factory = SharedPrefRepositoryViewModelFactory(SharedPrefRepository, this)
        viewModel = ViewModelProvider(this, factory)[SharedPrefRepositoryViewModel::class.java]

        binding.lifecycleOwner = this
        viewModel.getData()
        viewModel.firstName.observe(this, Observer {
            binding.tvName.text = it
        })
        binding.btnSave.setOnClickListener {
            viewModel.saveData(binding.etFname.text.toString(), binding.etLname.text.toString())
        }

    }
}