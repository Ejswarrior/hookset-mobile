package com.example.hookset_mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppNavHost(navController = rememberNavController(), modifier = Modifier, startDestination = "login")
        }
    }


}


@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = "login"
) {
    NavHost(navController = navController, startDestination =  startDestination, modifier = modifier) {
       composable("login") {Login().LoginPage(modifier = modifier)}
    }
}

@Composable
fun HooksetApp(modifier: Modifier) {
    Text(text = "Home")
}