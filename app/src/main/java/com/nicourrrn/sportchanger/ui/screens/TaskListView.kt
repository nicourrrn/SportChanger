package com.nicourrrn.sportchanger.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import com.nicourrrn.sportchanger.application.TasksViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun TaskList(viewModel: TasksViewModel = koinViewModel()){
    Column {
        viewModel.state.value.values.forEach {
            Text("${it.name}: ${it.done}")
        }
        TextButton(onClick = ( { viewModel.load() } )) {
            Text("Update")
        }
        TextButton(onClick = ({ viewModel.addTask() })) {
            Text("Add")
        }
    }
}
