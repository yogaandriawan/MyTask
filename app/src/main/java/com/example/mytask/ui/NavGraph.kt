package com.example.mytask.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

@Composable
fun TaskNavGraph(navController: NavHostController, viewModel: TaskViewModel) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            TaskListScreen(navController, viewModel)
        }
        composable(
            route = "add_edit/{taskId}",
            arguments = listOf(navArgument("taskId") { type = NavType.IntType })
        ) { backStackEntry ->
            val taskId = backStackEntry.arguments?.getInt("taskId") ?: 0
            TaskFormScreen(navController, viewModel, taskId)
        }
        composable(
            route = "detail/{taskId}",
            arguments = listOf(navArgument("taskId") { type = NavType.IntType })
        ) { backStackEntry ->
            val taskId = backStackEntry.arguments?.getInt("taskId") ?: 0
            TaskDetailScreen(navController, viewModel, taskId)
        }
    }
}