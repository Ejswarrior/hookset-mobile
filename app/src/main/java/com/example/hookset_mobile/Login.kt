package com.example.hookset_mobile

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ui.components.HooksetButton
import ui.components.HooksetInput.HooksetInput

class Login: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(modifier = Modifier) {
                LoginPage(modifier = Modifier)
            }
        }
    }
}
@Composable
fun LoginPage(modifier: Modifier = Modifier) {
    Surface(modifier = modifier
        .background(color = Color.White)
        .padding(vertical = 24.dp, horizontal = 24.dp)
        .fillMaxWidth()
        .fillMaxHeight(), color = Color.White) {
        Column{
            Row(modifier = Modifier.padding(bottom = 85.dp),verticalAlignment = Alignment.Top, horizontalArrangement = Arrangement.End) {
                Text(
                    text = "Login to Fishopedia",
                    color = Color.Black,
                    fontSize = 36.sp,
                    modifier = Modifier
                )
            }
            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                HooksetInput(modifier).HooktsetTextField("Email")

                HooksetInput(modifier).HooktsetTextField("Password")
                Row(modifier = Modifier.padding(top = 24.dp)) {
                    HooksetButton(modifier).button(variant = "primary", buttonText = "Login") {
                    }
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