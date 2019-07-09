package com.example.openmarket.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "comments")
data class Comment(
    @PrimaryKey @ColumnInfo(name = "id") var id: Long = 0,
    @ColumnInfo(name = "comment_data") var commentdata: String,
    @ColumnInfo(name = "comment_date") var dateOfComment: String,
    @ColumnInfo(name = "user_name") var userName: String
) : Serializable