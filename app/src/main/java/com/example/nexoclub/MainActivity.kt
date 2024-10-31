package com.example.nexoclub

import HomeScreen
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.nexoclub.roomDB.ClienteDatabase
import com.example.nexoclub.ui.theme.NEXOClubTheme
import com.example.nexoclub.viewModel.ClienteViewModel
import com.example.nexoclub.viewModel.Repository

class MainActivity : ComponentActivity() {
    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            ClienteDatabase::class.java,
            name = "cliente.db"
        ).build()
    }

    private val viewModel by viewModels<ClienteViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>) : T{
                    return ClienteViewModel(Repository(db)) as T
                }
            }
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        enableEdgeToEdge()
        window.statusBarColor = Color(0xFF003366).toArgb()
        window.navigationBarColor = Color(0xFF003366).toArgb()
        setContent {
            NEXOClubTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = "Login") {
                        composable(route = "Login") {
                            LoginScreen(modifier = Modifier.padding(innerPadding), viewModel, navController)
                        }
                        composable(route = "CriarConta") {
                            CriarContaScreen(modifier = Modifier.padding(innerPadding), viewModel, navController)
                        }
                        // Tela principal com BottomNavigation
                        composable(route = "Home/{id}") { backStackEntry ->
                            val id = backStackEntry.arguments?.getString("id")?.toIntOrNull() ?: -1
                            val homeNavController = rememberNavController()
                            Scaffold(
                                bottomBar = {
                                    BottomNavigationBar(navController = homeNavController)
                                }
                            ) { innerHomePadding ->
                                NavHost(
                                    navController = homeNavController,
                                    startDestination = BottomNavItem.Home.route,
                                    modifier = Modifier.padding(innerHomePadding)
                                ) {
                                    composable(BottomNavItem.Home.route) {
                                        HomeScreen()
                                    }
                                    composable(BottomNavItem.Profile.route) {
                                        ProfileScreen(id_cli = id, viewModel, navController)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: androidx.navigation.NavController) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Profile
    )

    NavigationBar(
        containerColor = Color(0xFF003366)
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        items.forEach { item ->
            NavigationBarItem(  // Alterado para NavigationBarItem (material3)
                icon = { Icon(imageVector = item.icon, contentDescription = item.title) },
                label = { Text(text = item.title) },
                selected = currentDestination?.route == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.White,
                    unselectedIconColor = Color.LightGray,
                    selectedTextColor = Color.White,
                    unselectedTextColor = Color.LightGray,
                    indicatorColor = Color(0xFF0055AA)
                )
            )
        }
    }
}

sealed class BottomNavItem(var title: String, var icon: ImageVector, var route: String) {
    object Home : BottomNavItem("Home", Icons.Default.Home, "home")
    object Profile : BottomNavItem("Minha Conta", Icons.Default.Person, "profile")
}