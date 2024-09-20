package com.example.hookset_mobile

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.hookset_mobile.modules.authModule
import com.example.hookset_mobile.modules.networkModule
import com.example.hookset_mobile.screens.Login.Login
import com.example.hookset_mobile.screens.posts.Posts
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
         startKoin{
             androidContext(this@MainActivity)
             modules(networkModule, authModule)
        }
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
       composable("login") { Login(navController = navController).LoginPage(modifier = modifier)}
        composable("posts"){ Posts(navController = navController).PostScreen() }
    }
}

