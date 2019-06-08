package com.example.openmarket.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "users")
data class User(
    @PrimaryKey @ColumnInfo(name = "id") val id:Long = 0,
    @ColumnInfo(name = "fullName") val fullName:String,
    @ColumnInfo(name = "username") val username:String,
    @ColumnInfo(name = "email") val email:String,
    @ColumnInfo(name = "password") val password:String,
    @ColumnInfo(name = "phoneNo") val phoneNo:String,
    @ColumnInfo(name = "pictureId") val pictureId:String,
    @ColumnInfo(name = "locationId") val locationId:String
    //@Ignore var products:List<Product>
):Serializable