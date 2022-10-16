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
import com.iglo.wigsapplicationn.databinding.RegisterLayoutBinding
import com.iglo.wigsapplicationn.view_model.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity() : AppCompatActivity() {
    val layoutResourceId = R.layout.register_layout
    lateinit var binding: RegisterLayoutBinding
    val vm: RegisterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(this), layoutResourceId, null, false
        )
        binding.lifecycleOwner = this
        setContentView(binding.root)
        binding.setVariable(BR.vm, vm)


        binding.buatAkunButton.setOnClickListener {
            if (binding?.username?.text.isNullOrEmpty() || binding?.password?.text.isNullOrEmpty()) {
                Toast.makeText(
                    this,
                    "Username dan password tidak boleh kosong",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                vm.register(
                    Login(
                        binding?.username?.text.toString(),
                        binding?.password?.text.toString()
                    )
                )
                Toast.makeText(
                    this,
                    "Berhasil Membuat Akun",
                    Toast.LENGTH_SHORT
                ).show()
                val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                startActivity(intent)
            }
        }

        binding.backToLogin.setOnClickListener {
            val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}