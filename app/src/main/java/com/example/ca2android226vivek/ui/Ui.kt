package com.example.ca2android226vivek.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ca2android226vivek.viewmodel.BillViewModel

@Composable
fun BillSplitterScreen(viewModel: BillViewModel) {
    val bill = viewModel.billState.collectAsState().value

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Name: " + bill.name, fontSize = 20.sp)
        Text(text = "Amount: " + bill.amount, fontSize = 20.sp)
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Split: " + bill.split, fontSize = 24.sp)
        
        Spacer(modifier = Modifier.height(20.dp))
        
        Row {
            Button(onClick = { viewModel.decreaseSplit() }) {
                Text(text = "-")
            }
            Spacer(modifier = Modifier.width(20.dp))
            Button(onClick = { viewModel.increaseSplit() }) {
                Text(text = "+")
            }
        }
    }
}
