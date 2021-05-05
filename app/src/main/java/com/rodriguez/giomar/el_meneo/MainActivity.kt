package com.rodriguez.giomar.el_meneo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.compose.*
import androidx.navigation.ui.NavigationUI
import com.google.android.gms.ads.MobileAds
import androidx.compose.material.Icon
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.rodriguez.giomar.el_meneo.ui.videoScreen.VideosScreen
import com.rodriguez.giomar.el_meneo.ui.videoScreen.VideosScreenViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//        val navController = Navigation.findNavController(this, R.id.activity_main_nav_host)
//        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
//        NavigationUI.setupWithNavController(bottomNavigationView, navController);
//        MobileAds.initialize(this)
        setContent {
            AppNavigation()
        }
    }

    @Composable
    fun AppNavigation() {
        val items = listOf(
            Screen.Profile,
            Screen.FriendsList,
        )
        val navController = rememberNavController()
        Scaffold(
            bottomBar = {
                BottomNavigation {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentRoute = navBackStackEntry?.arguments?.getString(KEY_ROUTE)
                    items.forEach { screen ->
                        BottomNavigationItem(
                            icon = { Icon(Icons.Filled.Favorite, contentDescription = "Localized description") },
                            label = { Text(stringResource(screen.resourceId)) },
                            selected = currentRoute == screen.route,
                            onClick = {
                                navController.navigate(screen.route) {
                                    // Pop up to the start destination of the graph to
                                    // avoid building up a large stack of destinations
                                    // on the back stack as users select items
                                    popUpTo = navController.graph.startDestination
                                    // Avoid multiple copies of the same destination when
                                    // reselecting the same item
                                    launchSingleTop = true
                                }
                            }
                        )
                    }
                }
            }
        ) {
            val listState = rememberLazyListState()
            //val model: VideosScreenViewModel = viewModel()
            NavHost(navController, startDestination = Screen.Profile.route) {
                composable(Screen.Profile.route) { HomeScreen(navController) }
                composable(Screen.FriendsList.route) {
                    VideosScreen(listState) { video ->
                        Log.d("Videos", video.title)

                    }
                }
            }
        }

    }
}

sealed class Screen(val route: String, @StringRes val resourceId: Int) {
    object Profile : Screen("profile", R.string.profile)
    object FriendsList : Screen("friendslist", R.string.friends_list)
}

@Composable
fun HomeScreen(navController: NavController) {
    Text(text = "Home Screen")
}