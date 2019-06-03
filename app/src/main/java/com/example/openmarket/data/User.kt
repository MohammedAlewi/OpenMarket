package com.example.openmarket.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id:Int,
    @ColumnInfo(name = "username") val username:String,
    @ColumnInfo(name = "password") val password:String,
    @ColumnInfo(name = "fullName") val fullName:String,
    @ColumnInfo(name = "phoneNo") val phoneNo:String,
    @ColumnInfo(name = "pictureId") val pictureId:String,
    @ColumnInfo(name = "locationId") val locationId:String
):Serializable