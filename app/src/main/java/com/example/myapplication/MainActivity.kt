package com.example.myapplication

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyApplicationTheme {
                ProfileScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen() {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("My Profile")
                },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.Menu, "Menu")
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.MoreVert, "More Options")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {}
            ) {
                Icon(Icons.Default.Add, "Add")
            }
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(16.dp))

            // Avatar
            Box(
                contentAlignment = Alignment.BottomEnd
            ) {
                Image(
                    painter = painterResource(id = R.drawable.navarez_photo),
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                        .border(4.dp, MaterialTheme.colorScheme.primaryContainer, CircleShape)
                        .background(MaterialTheme.colorScheme.primaryContainer),
                    contentScale = ContentScale.Crop
                )

                Box(
                    modifier = Modifier
                        .size(28.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.surface)
                        .padding(3.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(CircleShape)
                            .background(Color(0xFF4CAF50))
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Navarez",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            Text(
                text = "Android Developer",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.secondary
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Passionate about building beautiful and functional mobile experiences with Jetpack Compose.",
                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(horizontal = 24.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    onClick = {},
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(Icons.Default.Email, null, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Message")
                }

                OutlinedButton(
                    onClick = {},
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(Icons.Default.Person, null, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Follow")
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Stats Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 20.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    StatItem("128", "Posts")
                    VerticalDivider(modifier = Modifier.height(40.dp))
                    StatItem("4.2K", "Followers")
                    VerticalDivider(modifier = Modifier.height(40.dp))
                    StatItem("96", "Following")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Contact Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    ListItem(
                        headlineContent = { Text("navarez@liceo.edu.ph") },
                        supportingContent = { Text("Email") },
                        leadingContent = { Icon(Icons.Default.Email, null, tint = MaterialTheme.colorScheme.primary) }
                    )
                    HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp), thickness = 0.5.dp)
                    ListItem(
                        headlineContent = { Text("Cagayan de Oro, Philippines") },
                        supportingContent = { Text("Location") },
                        leadingContent = { Icon(Icons.Default.LocationOn, null, tint = MaterialTheme.colorScheme.primary) }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, name = "Light Mode")
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "Dark Mode"
)
@Composable
fun ProfilePreview() {
    MyApplicationTheme {
        ProfileScreen()
    }
}

@Composable
fun StatItem(
    number: String,
    label: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = number,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}