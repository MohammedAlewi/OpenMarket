package com.example.openmarket.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SubscriptionDao {
    @Query("SELECT  * FROM subscription WHERE subscribed_user = :username ")
    fun getUserSubscriptions(username: String): LiveData<List<Subscription>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSubscription(subscription: Subscription)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSubscriptions(subscription: List<Subscription>)
}