package com.example.mytask.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mytask.data.Task

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskListScreen(navController: NavController, viewModel: TaskViewModel) {
    val tasks = viewModel.allTasks.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Daftar Tugas Mahasiswa") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("add_edit/0") }) {
                Icon(Icons.Default.Add, contentDescription = "Tambah Tugas")
            }
        }
    ) { padding ->
        if (tasks.value.isEmpty()) {
            Text("Belum ada tugas. Tekan + untuk menambah.", modifier = Modifier.padding(padding).padding(16.dp))
        } else {
            LazyColumn(contentPadding = padding, modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp)) {
                items(tasks.value) { task ->
                    TaskCard(task) {
                        navController.navigate("detail/${task.id}")
                    }
                }
            }
        }
    }
}

@Composable
fun TaskCard(task: Task, onClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp).clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = task.judul, style = MaterialTheme.typography.titleLarge)
            Text(text = task.mataKuliah, style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Status: ${task.status} | Deadline: ${task.deadline}", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.secondary)
        }
    }
}