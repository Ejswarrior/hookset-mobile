package ui.components

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

sealed class BottomNavItem(val route: String, val icon: ImageVector, val label: String) {
    object Home : BottomNavItem("posts", Icons.Outlined.Home, "Home")
    object FishLog : BottomNavItem("fishlog", Icons.Outlined.Menu, "FishLog")
    object Profile : BottomNavItem("profile", Icons.Outlined.AccountCircle, "Profile")
}
@Composable
fun BottomNavigationBar(navController: NavController, navHost: @Composable () -> Unit ) {
    Scaffold(modifier = Modifier, bottomBar = {
        BottomNavigation {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            BottomNavigationItem(
                selected = currentRoute == BottomNavItem.Home.route,
                onClick = {
                    navController.navigate(BottomNavItem.Home.route) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                },
                icon = { Icon(BottomNavItem.Home.icon, contentDescription = null) },
                label = { Text(BottomNavItem.Home.label) }
            )
            BottomNavigationItem(
                selected = currentRoute == BottomNavItem.FishLog.route,
                onClick = {
                    navController.navigate(BottomNavItem.FishLog.route) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                },
                icon = { Icon(BottomNavItem.FishLog.icon, contentDescription = null) },
                label = { Text(BottomNavItem.FishLog.label) }
            )
            BottomNavigationItem(
                selected = currentRoute == BottomNavItem.Profile.route,
                onClick = {
                    navController.navigate(BottomNavItem.Profile.route) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                },
                icon = { Icon(BottomNavItem.Profile.icon, contentDescription = null) },
                label = { Text(BottomNavItem.Profile.label) }
            )
        }
    }) {paddingValues ->
        paddingValues
        navHost()
    }
}