package com.tugas.proplayervalorantwithcompose

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.getValue
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.tugas.proplayervalorantwithcompose.navigation.Screen
import com.tugas.proplayervalorantwithcompose.ui.theme.ProPlayerValorantWithComposeTheme

@Composable
fun ProPlayerValApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    Scaffold(
        bottomBar = {
            if (currentRoute != Screen.DetailPlayer.route) {
                BottomBar(navController)
            }
        },
        modifier = Modifier
    ) {
        innerPadding ->
        NavHost(navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    navigateToDetail = { playerId ->
                        navController.navigate(Screen.DetailPlayer.createRoute(playerId))
                    }
                )
            }
            composable(Screen.Profile.route) {
                ProfileScreen()
            }
            composable(
                route = Screen.DetailPlayer.route,
                arguments = listOf(navArgument("playerId",) {type = NavType.LongType}),
            ) {
                val id = it.arguments?.getLong("playerId") ?: -1L
                DetailScreen(playerId = id,
                    navigateBack = {
                        navController.navigateUp()
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProPlayerValAppPreview() {
    ProPlayerValorantWithComposeTheme {
        ProPlayerValApp()
    }
}