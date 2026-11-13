package com.example.mytask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.mytask.data.AppDatabase
import com.example.mytask.repository.TaskRepository
import com.example.mytask.ui.TaskNavGraph
import com.example.mytask.ui.TaskViewModel
import com.example.mytask.ui.theme.MyTaskTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. Inisialisasi Database dan Repository
        val database = AppDatabase.getDatabase(this)
        val repository = TaskRepository(database.taskDao())

        setContent {
            MyTaskTheme {
                val navController = rememberNavController()

                // 2. Setup ViewModel
                val viewModel: TaskViewModel = viewModel(
                    factory = object : ViewModelProvider.Factory {
                        @Suppress("UNCHECKED_CAST")
                        override fun <T : ViewModel> create(modelClass: Class<T>): T {
                            return TaskViewModel(repository) as T
                        }
                    }
                )

                // 3. Panggil NavGraph untuk merender Layar
                TaskNavGraph(navController = navController, viewModel = viewModel)
            }
        }
    }
}