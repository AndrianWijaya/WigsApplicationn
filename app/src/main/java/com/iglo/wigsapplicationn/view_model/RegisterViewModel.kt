package com.iglo.wigsapplicationn.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.iglo.common.entity.Login
import com.iglo.service.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    application: Application,
    private val loginRepository: LoginRepository
) : AndroidViewModel(application) {

    fun register(login: Login){
        loginRepository.insertUser(login)
    }
}