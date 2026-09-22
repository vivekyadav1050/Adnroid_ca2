package com.example.ca2android226vivek.viewmodel

import androidx.lifecycle.ViewModel
import com.example.ca2android226vivek.repository.BillRepository
import kotlinx.coroutines.flow.MutableStateFlow

class BillViewModel : ViewModel() {
    val repo = BillRepository()
    val billState = MutableStateFlow(repo.getBill())

    fun increaseSplit() {
        repo.incrementSplit()
        billState.value = repo.getBill().copy()
    }

    fun decreaseSplit() {
        repo.decrementSplit()
        billState.value = repo.getBill().copy()
    }
}
