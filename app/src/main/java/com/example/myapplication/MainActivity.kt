package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.myapplication.ui.theme.MyApplicationTheme
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.draw.blur
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.CameraAlt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    BusinessCard()
                }
            }
        }
    }
}

@Composable
fun BusinessCard() {
    val isDark = isSystemInDarkTheme()
    val glassColor = if (isDark) Color.Black.copy(alpha = 0.5f) else Color.White.copy(alpha = 0.4f)
    val textColor = Color.White
    val secondaryTextColor = Color.White.copy(alpha = 0.8f)
    val buttonBlue = Color(0xFF1976D2)

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Background Image
        Image(
            painter = painterResource(R.drawable.background_nature),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Dark Overlay for readability
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.4f))
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(40.dp))

            // Profile Image (Rounded Square like in the image)
            Image(
                painter = painterResource(R.drawable.navarez_photo),
                contentDescription = "Profile Photo",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(140.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .border(2.dp, Color.White.copy(alpha = 0.3f), RoundedCornerShape(24.dp))
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Charles David A. Navarez",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = textColor
                )
            )

            Text(
                text = "BSIT Student • Digital Creator",
                style = MaterialTheme.typography.bodyLarge,
                color = secondaryTextColor
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Glassmorphism Contact & Bio Container
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .graphicsLayer {
                        clip = true
                        shape = RoundedCornerShape(28.dp)
                    },
                color = glassColor,
                border = BorderStroke(1.dp, Color.White.copy(alpha = 0.2f))
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Contact Information",
                        style = MaterialTheme.typography.labelLarge,
                        color = secondaryTextColor,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    ContactRowGlass(
                        icon = Icons.Default.Email,
                        label = "cdnavarez29386@liceo.edu.ph"
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    ContactRowGlass(
                        icon = Icons.Default.Phone,
                        label = "+63 912 345 6789"
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Text(
                        text = "\"Hi, I'm Charles — a creative BSIT student who loves turning ideas into engaging, user-friendly designs.\"",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontStyle = FontStyle.Italic,
                            lineHeight = 20.sp
                        ),
                        color = textColor
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Social Buttons
            ActionButton(
                title = "My Website",
                subtitle = "Check out my portfolio",
                icon = Icons.Default.Language,
                backgroundColor = buttonBlue
            )

            Spacer(modifier = Modifier.height(16.dp))

            ActionButton(
                title = "Instagram",
                subtitle = "Follow me on Instagram",
                icon = Icons.Default.CameraAlt,
                backgroundColor = buttonBlue
            )
        }
    }
}

@Composable
fun ContactRowGlass(icon: ImageVector, label: String) {
    Surface(
        color = Color.White.copy(alpha = 0.1f),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = label,
                color = Color.White,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
fun ActionButton(
    title: String,
    subtitle: String,
    icon: ImageVector,
    backgroundColor: Color
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { },
        color = backgroundColor,
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(Color.White.copy(alpha = 0.2f), RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(imageVector = icon, contentDescription = null, tint = Color.White)
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(text = title, fontWeight = FontWeight.Bold, color = Color.White)
                Text(text = subtitle, style = MaterialTheme.typography.bodySmall, color = Color.White.copy(alpha = 0.7f))
            }

            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BusinessCardPreview() {
    MyApplicationTheme {
        BusinessCard()
    }
}

@Preview(showBackground = true, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
fun BusinessCardDarkPreview() {
    MyApplicationTheme {
        BusinessCard()
    }
}

@Preview(showBackground = true, fontScale = 1.5f, name = "Large Font")
@Composable
fun BusinessCardLargeFontPreview() {
    MyApplicationTheme {
        BusinessCard()
    }
}