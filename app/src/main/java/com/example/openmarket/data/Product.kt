package com.example.openmarket.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "products")
data class Product(
    @PrimaryKey @ColumnInfo(name = "product_id") var id: Long = 0,
    @ColumnInfo(name = "product_image") var imagePath: String,
    @ColumnInfo(name = "product_name") var name: String,
    @ColumnInfo(name = "product_type") var type: String,
    @ColumnInfo(name = "product_description") var description: String,
    @ColumnInfo(name = "product_amount") var amount: Int,
    @ColumnInfo(name = "product_price") var price: Double,
    @ColumnInfo(name = "product_date") var date: String,
    @ColumnInfo(name = "user_name") var userName: String
    //s@Ignore var comments:List<Comment>
) : Serializable