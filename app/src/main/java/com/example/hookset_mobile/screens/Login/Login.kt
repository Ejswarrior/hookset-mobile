package com.example.hookset_mobile.screens.Login

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hookset_mobile.AuthService
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.koin.android.ext.android.inject
import ui.components.HooksetButton
import ui.components.HooksetInput.HooksetInput

class Login(): ComponentActivity() {
    private val authService: AuthService by inject()
    private  val loginRepo: LoginRepository = LoginRepository(authService)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(modifier = Modifier) {
                LoginPage(modifier = Modifier)
            }
        }
    }

    private var email by mutableStateOf("")
    private var password by mutableStateOf("")
    private var hasError by mutableStateOf(false)


    private fun onChange(value: String, type: String) {
        if(type === "email") email = value
        else if(type === "password") password = value
    }

    private suspend fun login() {
        val loginResult = loginRepo.login(email, password)

        if(loginResult === "success") {
            //navigate
        }
        else hasError = true
    }

    @Composable
    fun LoginPage(modifier: Modifier = Modifier) {
        Surface(modifier = modifier
            .background(color = Color.White)
            .padding(top = 64.dp, bottom = 24.dp, start = 24.dp, end = 24.dp)
            .fillMaxWidth()
            .fillMaxHeight(), color = Color.White) {
            Column{
                Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {

                    Text(
                        text = "Welcome to Fish-dex",
                        color = Color.Black,
                        fontSize = 36.sp,

                        modifier = Modifier.padding(bottom = 48.dp)
                    )
                }


                Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                    if(hasError)Text(text = "Invalid Email or password", color = Color.Red, fontSize = 18.sp, modifier = Modifier.padding(bottom = 22.dp))
                    HooksetInput(modifier, "Email", email, onChange = { onChange(it, "email") }, hidden = false)

                    HooksetInput(modifier, "Password", password, onChange = { onChange(it, "password")}, hidden = true)
                    Row(modifier = Modifier.padding(top = 24.dp)) {
                        HooksetButton(modifier).button(variant = "primary", buttonText = "Login", disabled = if(email.isNotEmpty() && password.isNotEmpty()) false else true, onButtonClick = { runBlocking { launch {  login() }}})
                    }
                }

                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 125.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Forgot Password? Click Here.", fontSize = 16.sp, color = Color.Blue, modifier = Modifier.clickable { Log.d("textClick", "clicked") })
                }
            }


        }
    }
}
