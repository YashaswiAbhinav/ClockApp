package com.example.clockapplication

import androidx.compose.runtime.Composable
import androidx.navigation.NavController

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


@Composable
fun Navigation(navController: NavHostController){

    NavHost(navController=navController,startDestination="clock_screen"){
        composable(route = "clock_screen") {
            ClockScreen(navController)
        }
        composable(route = "timer_screen") {
            TimerScreen(navController)
        }
        composable(route = "stopwatch_screen") {
            StopwatchScreen(navController)
        }
    }

}