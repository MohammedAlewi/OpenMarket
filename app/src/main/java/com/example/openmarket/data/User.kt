package com.example.openmarket.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "users")
data class User(
    @PrimaryKey @ColumnInfo(name = "id") var id: Long = 0,
    @ColumnInfo(name = "fullName") var fullName: String,
    @ColumnInfo(name = "username") var username: String,
    @ColumnInfo(name = "email") var email: String,
    @ColumnInfo(name = "password") var password: String,
    @ColumnInfo(name = "phoneNo") var phoneNo: String,
    @ColumnInfo(name = "pictureId") var pictureId: String,
    @ColumnInfo(name = "locationId") var locationId: String
    //@Ignore var products:List<Product>
) : Serializable