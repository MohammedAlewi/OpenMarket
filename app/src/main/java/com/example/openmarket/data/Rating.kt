package com.example.openmarket.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "rating")
class Rating(
    @ColumnInfo(name = "username") var username: String,
    @ColumnInfo(name = "rate_no") var rateNo: Double,
    @ColumnInfo(name = "product_id") var product_id: Long,
    @PrimaryKey(autoGenerate = true) var id: Int = 0
) : Serializable