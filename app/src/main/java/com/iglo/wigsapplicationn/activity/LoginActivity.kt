package com.iglo.wigsapplicationn.activity

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.iglo.common.entity.Login
import com.iglo.wigsapplicationn.BR
import com.iglo.wigsapplicationn.R
import com.iglo.wigsapplicationn.activity.product.ProductListActivity
import com.iglo.wigsapplicationn.databinding.LoginLayoutBinding
import com.iglo.wigsapplicationn.view_model.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity() : AppCompatActivity() {
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
        binding.setVariable(BR.vm, vm)


        binding.loginButton.setOnClickListener {
            if (binding?.username?.text.isNullOrEmpty() || binding?.password?.text.isNullOrEmpty()) {
                Toast.makeText(
                    this,
                    "Username dan password tidak boleh kosong",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                vm.userLogin(
                    Login(
                        binding?.username?.text.toString(),
                        binding?.password?.text.toString(),
                    )
                )
                vm.loginState?.observe(this@LoginActivity) {
                    if (it.isSuccess){
                        val intent = Intent(this, ProductListActivity::class.java)
                        this.startActivity(intent)
                        Toast.makeText(
                            this,
                            "Berhasil Login",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    if (it.isFailure){
                        Toast.makeText(this,"Username dan Password Salah",Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }




        binding.registerAkun?.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}