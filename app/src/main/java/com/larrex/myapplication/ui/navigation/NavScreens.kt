package com.larrex.myapplication.ui.navigation

sealed class NavScreens(val route : String){

        object Splash : NavScreens("Splash")
        object FirstTime : NavScreens("FirstTime")
        object Home : NavScreens("Home")

}
