package com.example.ca2android226vivek.ui

import android.widget.Toast
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ca2android226vivek.model.CalculatedData
import com.example.ca2android226vivek.viewmodel.BillViewModel

@Composable
fun BillSplitterScreen(viewModel: BillViewModel) {
    val context = LocalContext.current

    var nameInput by remember { mutableStateOf("") }
    var amountInput by remember { mutableStateOf("") }
    var splitCount by remember { mutableIntStateOf(1) }

    var latestCalculatedData by remember { mutableStateOf<CalculatedData?>(null) }
    var recordsList by remember { mutableStateOf<List<CalculatedData>?>(null) }
    var showRecordsDialog by remember { mutableStateOf(value = false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = "Bill Splitter",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Name Input
        OutlinedTextField(
            value = nameInput,
            onValueChange = { nameInput = it },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Amount Input
        OutlinedTextField(
            value = amountInput,
            onValueChange = { amountInput = it },
            label = { Text("Amount") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Split Input Section (+ / -)
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(text = "Split:", fontSize = 20.sp, fontWeight = FontWeight.Medium)
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = { if (splitCount > 1) splitCount-- }) {
                Text(text = "-", fontSize = 20.sp)
            }
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = splitCount.toString(), fontSize = 22.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = { splitCount++ }) {
                Text(text = "+", fontSize = 20.sp)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Calculate and Check Record Buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            Button(
                onClick = {
                    val amount = amountInput.toDoubleOrNull()
                    if (nameInput.isBlank()) {
                        Toast.makeText(context, "Please enter a name", Toast.LENGTH_SHORT).show()
                    } else if (amount == null || (amount <= 0)) {
                        Toast.makeText(context, "Please enter a valid amount", Toast.LENGTH_SHORT).show()
                    } else {
                        // Call ViewModel parent function to calculate expense
                        val result = viewModel.calculateExpense(nameInput, amount, splitCount)
                        latestCalculatedData = result
                        Toast.makeText(context, "Done Complete", Toast.LENGTH_SHORT).show()
                    }
                },
            ) {
                Text(text = "Calculate")
            }

            OutlinedButton(
                onClick = {
                    // Fetch ArrayList of records from ViewModel
                    recordsList = viewModel.getData()
                    showRecordsDialog = true
                },
            ) {
                Text(text = "Check Record")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Display Latest Calculated Result
        latestCalculatedData?.let { data ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = "Latest Result:", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Name: ${data.name}", fontSize = 16.sp)
                    Text(text = "Total Amount: ₹${data.amount}", fontSize = 16.sp)
                    Text(text = "Split Among: ${data.split} person(s)", fontSize = 16.sp)
                    Text(
                        text = "Expense Per Person: ₹${"%.2f".format(data.expensePerPerson)}",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                    )
                }
            }
        }
    }

    // Dialog showing fetched records from ViewModel
    if (showRecordsDialog) {
        AlertDialog(
            onDismissRequest = { showRecordsDialog = false },
            title = { Text(text = "All Saved Records") },
            text = {
                val list = recordsList
                if (list.isNullOrEmpty()) {
                    Text(text = "No records found.")
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp),
                    ) {
                        items(list) { item ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp),
                                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
                            ) {
                                Column(modifier = Modifier.padding(12.dp)) {
                                    Text(text = "Name: ${item.name}", fontWeight = FontWeight.Bold)
                                    Text(text = "Amount: ₹${item.amount} | Split: ${item.split}")
                                    Text(text = "Expense / Person: ₹${"%.2f".format(item.expensePerPerson)}")
                                }
                            }
                        }
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = { showRecordsDialog = false }) {
                    Text("Close")
                }
            },
        )
    }
}
