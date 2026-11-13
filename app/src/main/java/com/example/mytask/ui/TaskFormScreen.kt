package com.example.mytask.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mytask.data.Task

@Composable
fun TaskFormScreen(navController: NavController, viewModel: TaskViewModel, taskId: Int) {
    val taskState = viewModel.getTask(taskId).collectAsState()
    val task = taskState.value

    var judul by remember { mutableStateOf("") }
    var mk by remember { mutableStateOf("") }
    var deskripsi by remember { mutableStateOf("") }
    var deadline by remember { mutableStateOf("") }
    var status by remember { mutableStateOf("Belum") }
    var prioritas by remember { mutableStateOf("Sedang") }

    LaunchedEffect(task) {
        if (task != null) {
            judul = task.judul
            mk = task.mataKuliah
            deskripsi = task.deskripsi
            deadline = task.deadline
            status = task.status
            prioritas = task.prioritas
        }
    }

    Column(modifier = Modifier.padding(16.dp).fillMaxSize()) {
        Text(if (taskId == 0) "Tambah Tugas Baru" else "Edit Tugas", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(value = judul, onValueChange = { judul = it }, label = { Text("Judul Tugas") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = mk, onValueChange = { mk = it }, label = { Text("Mata Kuliah") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = deskripsi, onValueChange = { deskripsi = it }, label = { Text("Deskripsi") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = deadline, onValueChange = { deadline = it }, label = { Text("Deadline (contoh: 15 Okt 2026)") }, modifier = Modifier.fillMaxWidth())

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            viewModel.saveTask(Task(id = taskId, mataKuliah = mk, judul = judul, deskripsi = deskripsi, deadline = deadline, prioritas = prioritas, status = status))
            navController.popBackStack()
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Simpan Tugas")
        }
    }
}