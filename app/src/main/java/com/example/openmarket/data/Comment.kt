package com.example.openmarket.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "comments")
data class Comment (
        @PrimaryKey(autoGenerate = true)  @ColumnInfo(name = "id")  var id:Long=0,
        @ColumnInfo(name = "comment_data") val commentdata:String,
        @ColumnInfo(name ="comment_date" )val dateOfComment:String,
        @ColumnInfo(name = "user_name") val userName:String
):Serializable