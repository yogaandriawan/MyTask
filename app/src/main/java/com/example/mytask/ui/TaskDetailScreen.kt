package com.example.mytask.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun TaskDetailScreen(navController: NavController, viewModel: TaskViewModel, taskId: Int) {
    val taskState = viewModel.getTask(taskId).collectAsState()
    val task = taskState.value

    if (task != null) {
        Column(modifier = Modifier.padding(16.dp).fillMaxSize()) {
            Text(text = task.judul, style = MaterialTheme.typography.headlineMedium)
            Text(text = "Mata Kuliah: ${task.mataKuliah}", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Deskripsi: ${task.deskripsi}")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Deadline: ${task.deadline}")
            Text(text = "Status: ${task.status}")
            Text(text = "Prioritas: ${task.prioritas}")

            Spacer(modifier = Modifier.height(24.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                Button(onClick = { navController.navigate("add_edit/${task.id}") }) {
                    Text("Edit")
                }
                Button(
                    onClick = {
                        viewModel.deleteTask(task)
                        navController.popBackStack()
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                ) {
                    Text("Hapus")
                }
            }
        }
    }
}