package com.iglo.wigsapplicationn.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.iglo.common.entity.Login
import com.iglo.service.auth.AuthDatabaseService
import com.iglo.service.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    application: Application,
    private val loginRepository: LoginRepository,
    authDatabaseService: AuthDatabaseService
) : AndroidViewModel(application) {

    var loginState: LiveData<Result<Boolean>>? = null
    val userLogged = authDatabaseService.isUserLoggedIn()

    fun userLogin(login: Login){
        viewModelScope.launch {
            loginState = loginRepository.getUser(login)
        }
    }
}