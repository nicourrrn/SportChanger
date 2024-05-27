package com.nicourrrn.sportchanger.application

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nicourrrn.sportchanger.domain.*
import kotlinx.coroutines.launch
import okhttp3.internal.toImmutableMap
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

class CounterViewModel(private val counterRepository: CounterRepository) : ViewModel() {
    private var _state = mutableStateOf(counterRepository.init())
    var state: State<Counter> = _state

    fun inc() {
        viewModelScope.launch {
            _state.value = _state.value.copy(count = _state.value.count + 1)
        }
    }

    fun reset() {
        viewModelScope.launch {
            _state.value = _state.value.copy(count = 0)
        }
    }
}

val counterAppModule = module {
    single<CounterRepository> { CounterRepositoryImpl() }
    viewModel { CounterViewModel(get()) }
}
class TasksViewModel(private val repo: TaskRepository) : ViewModel() {
    private var _state = mutableStateOf(mapOf<Int, Task>())
    var state: State<Map<Int, Task>> = _state

    fun load() {
        viewModelScope.launch {
            _state.value = repo.loadTasks()
        }
    }

    fun addTask() {
        var copy = state.value.toMutableMap()
        copy[state.value.size + 2] = Task("Hel", false)
        _state.value = copy.toImmutableMap()
    }
}
val appModule = module {
    single<TaskRepository> {TaskRepositoryImpl()}
    viewModel { TasksViewModel(get()) }
}
