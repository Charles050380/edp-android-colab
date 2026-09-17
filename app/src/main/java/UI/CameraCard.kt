package UI

package edu.liceo.fieldkit.ui

import android.Manifest
import androidx.camera.core.ImageCapture
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import edu.liceo.fieldkit.hardware.*
import edu.liceo.fieldkit.permissions.rememberPermission
import java.io.File

@Composable
fun CameraCard() {

    val context = LocalContext.current

    val camera = rememberPermission(
        Manifest.permission.CAMERA
    )

    val capture = remember {
        ImageCapture.Builder().build()
    }

    var photo by remember {
        mutableStateOf<File?>(null)
    }

    Card(
        Modifier.fillMaxWidth()
    ) {

        Column(
            Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            Text(
                "Field photo",
                style = MaterialTheme.typography.titleMedium
            )

            PermissionGate(
                state = camera,
                feature = "Camera",
                reason = "We need the camera to photograph the issue you report."
            ) {

                // TODO 9a
                CameraPreview(
                    capture = capture,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(240.dp)
                )

                // TODO 9b
                Button(
                    onClick = {
                        takePhoto(
                            context,
                            capture
                        ) { saved ->
                            photo = saved
                        }
                    }
                ) {
                    Text("Take photo")
                }

                // TODO 9c
                photo?.let {
                    Text("Saved: ${it.name}")
                }
            }

            photo?.let { f ->

                val thumb = remember(f) {
                    loadThumb(f)
                }

                thumb?.let {
                    Image(
                        bitmap = it,
                        contentDescription = "Last photo",
                        modifier = Modifier.size(96.dp)
                    )
                }
            }
        }
    }
}