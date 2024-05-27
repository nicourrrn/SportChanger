package com.nicourrrn.sportchanger.domain


interface CounterRepository {
    fun init() : Counter
    fun inc()
    fun dec()
}

interface TaskRepository {
    fun addTask(task: Task) : Int
    fun checkTask(taskId: Int): Boolean
    suspend fun loadTasks(): Map<Int, Task>
}
