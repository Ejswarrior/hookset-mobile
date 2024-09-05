package com.example.hookset_mobile.screens.Login

import com.example.hookset_mobile.AuthService

class LoginRepository(private val authService: AuthService) {

    suspend fun login(email: String, password: String): String {
        val loginResult = authService.logIn(email, password)

        return loginResult
    }
}