package com.example.openmarket.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    //primaryKeys = ["user_id","product_id"],
    foreignKeys = [
        ForeignKey(entity = User::class,parentColumns = ["id"],childColumns = ["user_id"],onDelete = ForeignKey.CASCADE),
        ForeignKey(entity = Product::class,parentColumns = ["product_id"],childColumns = ["product_id"],onDelete = ForeignKey.CASCADE)
    ]
)
data class UserProductJoin(
    @PrimaryKey @ColumnInfo(name="user_id") var user_id:Long,
    @ColumnInfo(name = "product_id") var product_id:Long
)
