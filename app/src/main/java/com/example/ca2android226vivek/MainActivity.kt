package com.example.ca2android226vivek

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.ca2android226vivek.ui.BillSplitterScreen
import com.example.ca2android226vivek.ui.theme.Ca2android226vivekTheme
import com.example.ca2android226vivek.viewmodel.BillViewModel

class MainActivity : ComponentActivity() {
    private val viewModel = BillViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Ca2android226vivekTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Surface(modifier = Modifier.padding(innerPadding)) {
                        BillSplitterScreen(viewModel = viewModel)
                    }
                }
            }
        }
    }
}
