package com.nicourrrn.sportchanger

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.twotone.ArrowBack
import androidx.compose.material.icons.automirrored.twotone.List
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.nicourrrn.sportchanger.application.*
import com.nicourrrn.sportchanger.ui.screens.CounterView
import com.nicourrrn.sportchanger.ui.screens.TaskList
import com.nicourrrn.sportchanger.ui.theme.SportChangerTheme
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import androidx.navigation.compose.composable
import org.koin.androidx.compose.koinViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold (
        bottomBar = { BottomNavigation {
            var actualRoute = remember {
                ""
            }
            BottomNavigationItem(selected = actualRoute == "/tasks" ,
                onClick = { navController.navigate("/tasks"); actualRoute = "/tasks"},
                icon = { Icon(Icons.AutoMirrored.TwoTone.List, "Tasks")},
                label = { Text(text = "Tasks")})
            BottomNavigationItem(selected = actualRoute == "/counter" ,
                onClick = { navController.navigate("/counter"); actualRoute = "/counter"},
                icon = { Icon(Icons.Outlined.Add, "Counter")},
                label = { Text(text = "Counter")})
        } }
    ) {padding ->
        NavHost(navController = navController, startDestination = "/") {
            composable("/") {
                Scaffold { padding ->
                    Row(modifier = Modifier
                        .padding(padding)
                        .fillMaxSize(), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                        TextButton(onClick = { navController.navigate("/tasks") }) {
                            Text("To tasks")
                        }
                        TextButton(onClick = { navController.navigate("/counter") }) {
                            Text("To counter")
                        }
                    }
                }
            }
            composable("/tasks") {
                Scaffold(
                    topBar = {
                        TopAppBar(title = { Text("Task list") }, actions = {
                            IconButton(onClick = { navController.popBackStack() }) {
                                Icon(Icons.AutoMirrored.TwoTone.ArrowBack, "Back")
                            }
                        })
                    }
                ) {
                    Surface(modifier = Modifier.padding(it)) {
                        TaskList()
                    }
                }
            }
            composable("/counter") {
                val viewModel: CounterViewModel = koinViewModel()

                Scaffold(
                    topBar = {
                        TopAppBar(title = { Text("Counter") }, actions = {
                            IconButton(onClick = { navController.popBackStack() }) {
                                Icon(Icons.AutoMirrored.TwoTone.ArrowBack, "Back")
                            }
                        })
                    }, floatingActionButton = {
                        FloatingActionButton(onClick = { viewModel.reset() }) {
                            Icon(Icons.Outlined.Refresh, "Refresh")
                        }
                    }
                ) {
                    Surface(modifier = Modifier.padding(it)) {
                        CounterView(viewModel = viewModel)
                    }
                }
            }
        }
    }

}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SportChangerTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    MainScreen()
                }
            }
        }
    }
}

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(appModule, counterAppModule)
        }
    }
}
