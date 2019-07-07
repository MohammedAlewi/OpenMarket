package com.example.openmarket.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RatingDao {
    @Query("SELECT * FROM rating WHERE product_id = :productId ")
    fun selectAllRatingForProduct(productId: Long): LiveData<List<Rating>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRating(rating: Rating)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRatings(ratings: List<Rating>)

    @Query("SELECT * FROM rating WHERE username = :username")
    fun getRatingUsingUsername(username: String): LiveData<List<Rating>>
}