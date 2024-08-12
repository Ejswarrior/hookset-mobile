package com.example.hookset_mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.example.hookset_mobile.ui.theme.Hookset_mobileTheme
import androidx.compose.ui.unit.sp

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
    Surface(modifier = modifier
        .padding(vertical = 24.dp, horizontal = 24.dp)
        .fillMaxWidth()
        .fillMaxHeight(), color = Color.White) {
        Row(modifier = Modifier,verticalAlignment = Alignment.Top, horizontalArrangement = Arrangement.Center) {
            Text(
                text = "Login to Fishopedia",
                color = Color.Black,
                fontSize = 36.sp,
                modifier = Modifier
            )
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