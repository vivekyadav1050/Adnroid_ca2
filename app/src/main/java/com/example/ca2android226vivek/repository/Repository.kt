package com.example.ca2android226vivek.repository

import com.example.ca2android226vivek.model.BillModel

class BillRepository {
    private var currentModel = BillModel()

    fun getBill(): BillModel = currentModel

    fun incrementSplit(): BillModel {
        currentModel = currentModel.copy(split = currentModel.split + 1)
        return currentModel
    }

    fun decrementSplit(): BillModel {
        if (currentModel.split > 1) {
            currentModel = currentModel.copy(split = currentModel.split - 1)
        }
        return currentModel
    }
}
