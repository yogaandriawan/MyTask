package com.example.mytask.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mytask.data.Task
import com.example.mytask.repository.TaskRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TaskViewModel(private val repository: TaskRepository) : ViewModel() {

    // StateFlow agar UI otomatis update jika ada perubahan di Database
    val allTasks: StateFlow<List<Task>> = repository.allTasks.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    fun getTask(id: Int): StateFlow<Task?> {
        return repository.getTaskStream(id).stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = null
        )
    }

    fun saveTask(task: Task) {
        viewModelScope.launch {
            if (task.id == 0) {
                repository.insert(task)
            } else {
                repository.update(task)
            }
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            repository.delete(task)
        }
    }
}