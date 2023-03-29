package com.larrex.myapplication.ui.navigation

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.larrex.myapplication.ui.FirstTimeScreen
import com.larrex.myapplication.ui.HomeScreen
import com.larrex.myapplication.ui.SplashScreen
import com.larrex.myapplication.ui.viewmodel.MainViewModel

@Composable
fun MainNavGraph(
    navHostController: NavHostController,
    application: Application,
    viewModel: MainViewModel
) {

    NavHost(navController = navHostController, startDestination = NavScreens.Splash.route) {

        composable(NavScreens.Splash.route) {
            SplashScreen(navController = navHostController)
        }
        composable(NavScreens.FirstTime.route) {
            FirstTimeScreen(navHostController)
        }
        composable(NavScreens.Home.route) {
            HomeScreen(viewModel)
        }
    }

}