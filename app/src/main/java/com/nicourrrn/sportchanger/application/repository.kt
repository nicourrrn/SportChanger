package com.nicourrrn.sportchanger.application

import com.nicourrrn.sportchanger.domain.*
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json

class CounterRepositoryImpl: CounterRepository {

    override fun init(): Counter {
       return Counter(0)
    }

    override fun inc() {
        TODO("Not yet implemented")
    }

    override fun dec() {
        TODO("Not yet implemented")
    }

}
class TaskRepositoryImpl : TaskRepository {
    private val url = "http://192.168.0.101:8000"
    private val _client = HttpClient(OkHttp) {
        expectSuccess = true
        install(Logging)
        install(ContentNegotiation) {
            json()
        }
    }
    override suspend fun loadTasks(): Map<Int, Task>{
        return _client.get("$url/tasks").body()
    }

    override fun checkTask(taskId: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun addTask(task: Task): Int {
        TODO("Late")
    }
}
