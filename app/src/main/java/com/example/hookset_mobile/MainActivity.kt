package com.example.hookset_mobile

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.safeDrawingPadding
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
import com.example.hookset_mobile.screens.Posts.Posts
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ui.components.BottomNavigationBar

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
         startKoin{
             androidContext(this@MainActivity)
             modules(networkModule, authModule)
        }
        val authService: AuthService by inject()


        setContent {
            Box(modifier = Modifier.safeDrawingPadding()) {

                val navController = rememberNavController()

                BottomNavigationBar(navController = navController) {
                    AppNavHost(navController = navController, modifier = Modifier, startDestination = "start", authService = authService)
                }
            }

        }
    }


}

fun startScreen(authService: AuthService, navController: NavHostController){
    Log.d("startScreen", "in start screen")
    runBlocking {
        launch {
            val loginStatus = authService.getAuthToken()
            Log.d("loginStatus", loginStatus.toString())
            if(loginStatus != null){
                navController.navigate("login")
            }
            else navController.navigate("login")
        }
    }
    Log.d("startScreen", "afterStartScreen")

}

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = "login",
    authService: AuthService,
) {
    NavHost(navController = navController, startDestination =  startDestination, modifier = modifier) {
        composable("start") { startScreen(authService, navController)}
        composable("login") { Login(navController = navController).LoginPage(modifier = modifier)}
        composable("posts"){ Posts(navController = navController).PostScreen() }
    }
}

