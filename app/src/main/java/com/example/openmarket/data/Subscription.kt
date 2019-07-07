package com.example.openmarket.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "subscription")
data class Subscription(
    @PrimaryKey @ColumnInfo(name="subscribed_to") var subscribed_to:String,
    @ColumnInfo(name = "subscribed_user") var username:String
):Serializable