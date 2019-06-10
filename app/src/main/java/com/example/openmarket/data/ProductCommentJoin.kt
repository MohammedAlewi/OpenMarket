package com.example.openmarket.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    //primaryKeys = ["product_id","comment_id"],
    foreignKeys = [
        ForeignKey(entity = Comment::class,parentColumns = ["id"],childColumns = ["comment_id"],onDelete =ForeignKey.CASCADE),
        ForeignKey(entity = Product::class,parentColumns = ["product_id"],childColumns = ["product_id"],onDelete =ForeignKey.CASCADE)
    ]
)
data class ProductCommentJoin(
    @PrimaryKey @ColumnInfo(name="comment_id") var comment_id:Long,
    @ColumnInfo(name = "product_id") var product_id:Long
)