package com.example.shoppinglist.navigation

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.shoppinglist.screens.HomeScreen
import com.example.shoppinglist.screens.ProfileScreen
import com.example.shoppinglist.screens.SettingScreen
import com.example.shoppinglist.ui.theme.ShoppingListTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun MainScreen(
) {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val items = listOf(Screen.Home, Screen.Profile)

    var isDarkMode by rememberSaveable { mutableStateOf(false) }

    ShoppingListTheme(darkTheme = isDarkMode) {
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                ModalDrawerSheet {
                    Text(
                        text = "Menu",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                        modifier = Modifier.padding(16.dp)
                    )
                    HorizontalDivider(Modifier, DividerDefaults.Thickness, DividerDefaults.color)

                    NavigationDrawerItem(
                        label = { Text("Settings") },
                        selected = false,
                        onClick = {
                            scope.launch { drawerState.close() }
                            navController.navigate(Screen.Setting.route)
                        },
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )

                    HorizontalDivider(
                        modifier = Modifier.padding(vertical = 8.dp),
                        thickness = DividerDefaults.Thickness,
                        color = DividerDefaults.color
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = if (isDarkMode) "Dark Mode" else "Light Mode")
                        Switch(
                            checked = isDarkMode,
                            onCheckedChange = { isDarkMode = it },
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = colorScheme.primary,
                                checkedTrackColor = colorScheme.primaryContainer,
                                uncheckedThumbColor = colorScheme.onSurfaceVariant,
                                uncheckedTrackColor = colorScheme.surfaceVariant
                            )
                        )
                    }
                }
            }
        ) {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text("Shopping List") },
                        navigationIcon = {
                            IconButton(onClick = { scope.launch { drawerState.open() } }) {
                                Icon(Icons.Default.Menu, contentDescription = "Menu")
                            }
                        }
                    )
                },
                bottomBar = {
                    NavigationBar {
                        items.forEach { screen ->
                            val navBackStackEntry by navController.currentBackStackEntryAsState()
                            val currentDestination = navBackStackEntry?.destination?.route
                            NavigationBarItem(
                                selected = currentDestination == screen.route,
                                onClick = {
                                    navController.navigate(screen.route) {
                                        popUpTo(Screen.Home.route) { inclusive = false }
                                        launchSingleTop = true
                                    }
                                },
                                icon = { Icon(screen.icon, contentDescription = screen.title) },
                                label = { Text(screen.title) }
                            )
                        }
                    }
                }
            ) { innerPadding: PaddingValues ->
                    NavHost(
                        navController = navController,
                        startDestination = Screen.Home.route,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(
                            route = Screen.Home.route,
                            enterTransition = {slideInHorizontally(initialOffsetX = { -1000 }, animationSpec = tween(300)) },
                            exitTransition = {slideOutHorizontally(targetOffsetX = { -1000 }, animationSpec = tween(300)) }
                        ) {
                            HomeScreen()
                        }

                        composable(
                            route = Screen.Profile.route,
                            enterTransition = {slideInHorizontally(initialOffsetX = { 1000 }, animationSpec = tween(300)) },
                            exitTransition = {slideOutHorizontally(targetOffsetX = { -1000 }, animationSpec = tween(300)) }
                        ) {
                            ProfileScreen()
                        }

                        composable(
                            route = Screen.Setting.route,
                            enterTransition = {slideInHorizontally(initialOffsetX = { 1000 }, animationSpec = tween(300)) },
                            exitTransition = {slideOutHorizontally(targetOffsetX = { -1000 }, animationSpec = tween(300)) }
                        ) {
                            SettingScreen()
                        }
                    }
            }
        }
    }
}


