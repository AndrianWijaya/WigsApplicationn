package com.iglo.wigsapplicationn.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.iglo.wigsapplicationn.R
import com.iglo.wigsapplicationn.activity.product.Product
import com.iglo.wigsapplicationn.databinding.LoginLayoutBinding
import com.iglo.wigsapplicationn.view_model.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Login() : AppCompatActivity() {
    val layoutResourceId = R.layout.login_layout
    lateinit var binding: LoginLayoutBinding
    val vm: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(this), layoutResourceId, null, false
        )
        binding.lifecycleOwner = this
        setContentView(binding.root)

        binding.loginButton.setOnClickListener{
            val intent = Intent(this, Product::class.java)
            this.startActivity(intent)
        }
    }
}