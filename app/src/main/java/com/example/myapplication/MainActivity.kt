package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.myapplication.ui.theme.ProfileCardLabTheme
import kotlinx.serialization.Serializable

// 1. Define routes
@Serializable
object Home

@Serializable
data class Greeting(val userName: String)

// 2. Main Activity
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProfileCardLabTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = Home) {
                        composable<Home> {
                            HomeScreen(onShowGreeting = { typedName: String ->
                                navController.navigate(Greeting(userName = typedName))
                            })
                        }
                        composable<Greeting> { backStackEntry ->
                            val greeting: Greeting = backStackEntry.toRoute()
                            GreetingScreen(userName = greeting.userName)
                        }
                    }
                }
            }
        }
    }
}

// 3. Screen Composables
@Composable
fun HomeScreen(onShowGreeting: (String) -> Unit) {
    var name by remember { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Greeting App",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(32.dp))
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Type your name") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { if (name.isNotBlank()) onShowGreeting(name) },
            modifier = Modifier.fillMaxWidth(),
            enabled = name.isNotBlank()
        ) {
            Text("Show Greeting")
        }
    }
}

@Composable
fun GreetingScreen(userName: String) {
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Hello, $userName!",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Welcome to Jetpack Navigation.",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.secondary
        )
    }
}

// 4. Previews
@Preview(showBackground = true, name = "Home Screen")
@Composable
fun HomePreview() {
    ProfileCardLabTheme {
        Surface {
            HomeScreen(onShowGreeting = {})
        }
    }
}

@Preview(showBackground = true, name = "Greeting Screen")
@Composable
fun GreetingPreview() {
    ProfileCardLabTheme {
        Surface {
            GreetingScreen(userName = "Charles")
        }
    }
}
