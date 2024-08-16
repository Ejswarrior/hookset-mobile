package com.example.hookset_mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController

import com.example.hookset_mobile.ui.theme.Hookset_mobileTheme

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


@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavController,
    startDestination: String = NavigationItem
)
@Composable
fun HooksetApp(modifier: Modifier) {
    Text(text = "Home")
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Hookset_mobileTheme {
        HooksetApp(modifier = Modifier)
    }
}