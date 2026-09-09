package com.example.myapplication

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun DishListScreen(
    viewModel: DishViewModel,
    onDishClick: (Int) -> Unit
) {
    // State flows DOWN from the ViewModel into this screen.
    val dishes by viewModel.dishes.collectAsStateWithLifecycle()

    // Local UI state: what is currently typed, and which dish is being renamed.
    var newDishName by remember { mutableStateOf("") }
    var dishBeingEdited by remember { mutableStateOf<Dish?>(null) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {

        Text("My Dishes", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(12.dp))

        // --------- CREATE (given) ---------
        Row(verticalAlignment = Alignment.CenterVertically) {
            OutlinedTextField(
                value = newDishName,
                onValueChange = { newDishName = it },
                label = { Text("New dish name") },
                singleLine = true,
                modifier = Modifier.weight(1f)
            )
            Spacer(Modifier.width(8.dp))
            Button(onClick = {
                viewModel.addDish(newDishName)
                newDishName = "" // clear the box after adding
            }) { Text("Add") }
        }

        Spacer(Modifier.height(16.dp))

        // --------- READ (given) ---------
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(items = dishes, key = { it.id }) { dish ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onDishClick(dish.id) } // opens Screen 2
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(dish.name, style = MaterialTheme.typography.titleMedium)
                            Text(
                                "${dish.recipes.size} step(s)",
                                style = MaterialTheme.typography.bodySmall
                            )
                        }

                        TextButton(onClick = { dishBeingEdited = dish }) { Text("Edit") }

                        TextButton(onClick = {
                            // TODO 7 (10 pts): delete THIS dish through the ViewModel.
                            viewModel.deleteDish(dish.id)
                        }) { Text("Delete") }
                    }
                }
            }
        }

        // --------- UPDATE dialog ---------
        val editing = dishBeingEdited
        if (editing != null) {
            EditDialog(
                title = "Rename dish",
                initialText = editing.name,
                onConfirm = { newName ->
                    // TODO 8 (15 pts): rename this dish through the ViewModel,
                    // then close the dialog by setting dishBeingEdited = null.
                    viewModel.updateDish(editing.id, newName)
                    dishBeingEdited = null
                },
                onDismiss = { dishBeingEdited = null }
            )
        }
    }
}

// --------- Reusable dialog: GIVEN, use it on BOTH screens ---------
@Composable
fun EditDialog(
    title: String,
    initialText: String,
    onConfirm: (String) -> Unit,
    onDismiss: () -> Unit
) {
    var text by remember { mutableStateOf(initialText) }
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(title) },
        text = {
            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
                singleLine = true
            )
        },
        confirmButton = { TextButton(onClick = { onConfirm(text) }) { Text("Save") } },
        dismissButton = { TextButton(onClick = onDismiss) { Text("Cancel") } }
    )
}
