package com.example.weatherapplication.ui.theme.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.weatherapplication.ui.theme.screens.mainScreen
import com.example.weatherapplication.ui.theme.application.MainViewModel

import com.example.weatherapplication.ui.theme.screens.search.SearchViewModel
import com.example.weatherapplication.ui.theme.screens.searchScreen
import com.example.weatherapplication.ui.theme.screens.splashScreen


@Composable
fun navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screens.SplashScreen.name) {
        composable(route = Screens.SplashScreen.name) {
            splashScreen(navController)
        }

        val route = Screens.MainScreen.name
        composable(
            route = "$route/{city}",
            arguments = listOf(
                navArgument(name = "city") {
                    type = NavType.StringType
                })
        ) { navBack ->
            navBack.arguments?.getString("city").let { city ->

                val mainViewModel = hiltViewModel<MainViewModel>()

                mainScreen(
                    navController = navController, mainViewModel, city = city
                )
            }


        }

        composable(route = Screens.SearchScreen.name) {
            val searchViewModel = hiltViewModel<SearchViewModel>()
            searchScreen(navController,searchViewModel)

        }

    }
}
