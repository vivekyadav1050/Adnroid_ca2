package com.example.ca2android226vivek.viewmodel

import androidx.lifecycle.ViewModel
import com.example.ca2android226vivek.model.BillModel
import com.example.ca2android226vivek.repository.BillRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class BillViewModel : ViewModel() {
    private val repository = BillRepository()
    
    private val _billState = MutableStateFlow(repository.getBill())
    val billState: StateFlow<BillModel> = _billState.asStateFlow()

    fun increaseSplit() {
        _billState.value = repository.incrementSplit()
    }

    fun decreaseSplit() {
        _billState.value = repository.decrementSplit()
    }
}
