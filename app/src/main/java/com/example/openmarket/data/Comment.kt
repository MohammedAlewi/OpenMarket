package com.example.openmarket.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "comments")
data class Comment (
        @PrimaryKey(autoGenerate = true)  @ColumnInfo(name = "id")  val id:Long,
        @ColumnInfo(name = "comment_data") val commentdata:String,
        @ColumnInfo(name ="comment_date" )val dateOfComment:String,
        @ColumnInfo(name = "user_name") val userName:String
):Serializable