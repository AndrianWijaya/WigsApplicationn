package com.iglo.service.auth

import com.iglo.common.entity.Login

interface AuthDatabaseService {
    fun login(login: Login)
    fun isUserLoggedIn(): Boolean
    fun getUser(): String
}