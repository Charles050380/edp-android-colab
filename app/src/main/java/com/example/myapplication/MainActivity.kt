package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable

// 1. Routes
@Serializable
object Home

@Serializable
data class Greeting(val userName: String)

// 2. Activity
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Using default MaterialTheme for debugging
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White // Force white background
                ) {
                    val navController = rememberNavController()
                    
                    NavHost(
                        navController = navController, 
                        startDestination = Home
                    ) {
                        composable<Home> {
                            HomeScreen(onNavigate = { name ->
                                navController.navigate(Greeting(userName = name))
                            })
                        }
                        composable<Greeting> { backStackEntry ->
                            val route: Greeting = backStackEntry.toRoute()
                            GreetingScreen(
                                userName = route.userName,
                                onBack = { navController.popBackStack() }
                            )
                        }
                    }
                }
            }
        }
    }
}

// 3. Screens
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(onNavigate: (String) -> Unit) {
    var textState by remember { mutableStateOf("") }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Navigation App") }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Enter Your Name",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.Black // Force black text
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            OutlinedTextField(
                value = textState,
                onValueChange = { textState = it },
                label = { Text("Name") },
                modifier = Modifier.fillMaxWidth()
            )
            
            Spacer(modifier = Modifier.height(24.dp))
            
            Button(
                onClick = { if (textState.isNotBlank()) onNavigate(textState) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Go to Greeting")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GreetingScreen(userName: String, onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Greeting") },
                navigationIcon = {
                    TextButton(onClick = onBack) {
                        Text("Back")
                    }
                }
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Hello, $userName!\nWelcome to Navigation.",
                style = MaterialTheme.typography.headlineLarge,
                textAlign = TextAlign.Center,
                color = Color.Black // Force black text
            )
        }
    }
}

// 4. Previews
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MaterialTheme {
        HomeScreen(onNavigate = {})
    }
}
