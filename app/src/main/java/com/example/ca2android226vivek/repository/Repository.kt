package com.example.ca2android226vivek.repository

import com.example.ca2android226vivek.model.CalculatedData

class BillRepository {
    private val records = ArrayList<CalculatedData>()

    fun addRecord(record: CalculatedData) {
        records.add(record)
    }

    fun getRecords(): ArrayList<CalculatedData> {
        return records
    }
}
