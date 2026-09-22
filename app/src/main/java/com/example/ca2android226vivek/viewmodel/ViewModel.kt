package com.example.ca2android226vivek.viewmodel

import androidx.lifecycle.ViewModel
import com.example.ca2android226vivek.model.CalculatedData
import com.example.ca2android226vivek.repository.BillRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class BillViewModel : ViewModel() {
    private val repo = BillRepository()

    private val _recordsState = MutableStateFlow<ArrayList<CalculatedData>>(ArrayList())
    val recordsState: StateFlow<ArrayList<CalculatedData>> = _recordsState.asStateFlow()

    // Parent function to calculate expense split
    fun calculateExpense(name: String, amount: Double, split: Int): CalculatedData {
        val expense = if (split > 0) amount / split else 0.0
        val record = CalculatedData(
            name = name,
            amount = amount,
            split = split,
            expensePerPerson = expense,
        )
        repo.addRecord(record)
        _recordsState.value = ArrayList(repo.getRecords())
        return record
    }

    // Function to get data returning ArrayList of all records
    fun getData(): ArrayList<CalculatedData> {
        return repo.getRecords()
    }
}
