package com.nicourrrn.sportchanger.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import com.nicourrrn.sportchanger.application.CounterViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun CounterView(viewModel: CounterViewModel = koinViewModel()) {
    Column( modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally ) {
        Text("Counter: ${viewModel.state.value.count}", fontSize = TextUnit(8f, TextUnitType.Em))
        TextButton(onClick = { viewModel.inc() }) {
            Text("Increment")
        }
    }
}