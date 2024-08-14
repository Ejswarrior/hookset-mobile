package com.example.hookset_mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hookset_mobile.ui.theme.Hookset_mobileTheme
import ui.components.HooksetButton
import ui.components.HooksetInput.HooksetInput

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Hookset_mobileTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    HooksetApp(modifier = Modifier)
                }
            }
        }
    }
}

fun inputString(changeString: String): String {
    return changeString
}

@Composable
fun HooksetApp(modifier: Modifier = Modifier) {
    Surface(modifier = modifier.background(color = Color.White)
        .padding(vertical = 24.dp, horizontal = 24.dp)
        .fillMaxWidth()
        .fillMaxHeight(), color = Color.White) {
        Column{
            Row(modifier = Modifier.padding(bottom = 85.dp),verticalAlignment = Alignment.Top, horizontalArrangement = Arrangement.Center) {
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
                    HooksetButton(modifier).button(variant = "primary", buttonText = "Login") {

                    }
            }
        }


    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Hookset_mobileTheme {
        HooksetApp(modifier = Modifier)
    }
}