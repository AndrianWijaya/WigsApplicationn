package com.iglo.service.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.iglo.common.entity.Login
import com.iglo.common.untils.AppExecutors
import com.iglo.service.auth.AuthDatabaseService
import com.iglo.service.dao.LoginDAO

class LoginRepository(
    private val loginDAO: LoginDAO,
    private val appExecutors: AppExecutors,
    private val authDatabaseService: AuthDatabaseService
) {
    private val loginResult = MediatorLiveData<Result<Boolean>>()

    fun getUser(user: Login): LiveData<Result<Boolean>> {
        val localData = loginDAO.getUser(user.user)
        loginResult.addSource(localData) {
            when {
                it == null ->
                    loginResult.value = Result.failure(Throwable("User tidak tersedia"))
                it.password != user.password ->
                    loginResult.value = Result.failure(Throwable("User tidak tersedia"))
                else -> {
                    loginResult.value = Result.success(true)
                    authDatabaseService.login(user)
                }
            }
        }
        return loginResult
    }

    fun getUser2(user: Login): LiveData<Login> {
        return loginDAO.getUser(user.user)
    }

    fun insertUser(user: Login) {
        appExecutors.diskIO.execute {
            loginDAO.registerUser(user)
        }
    }
}