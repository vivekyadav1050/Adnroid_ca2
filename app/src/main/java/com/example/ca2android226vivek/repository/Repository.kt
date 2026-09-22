package com.example.ca2android226vivek.repository

import com.example.ca2android226vivek.model.BillModel

class BillRepository {
    val model = BillModel()

    fun getBill(): BillModel {
        return model
    }

    fun incrementSplit(): BillModel {
        model.split = model.split + 1
        return model
    }

    fun decrementSplit(): BillModel {
        if (model.split > 1) {
            model.split = model.split - 1
        }
        return model
    }
}
