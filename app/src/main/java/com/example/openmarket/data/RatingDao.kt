package com.example.openmarket.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface RatingDao {
    @Query("SELECT * FROM rating WHERE product_id = :productId ")
    fun selectAllRatingForProduct(productId: Long): LiveData<List<Rating>>

    @Query("SELECT * FROM rating ")
    fun selectAllRating(): List<Rating>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRating(rating: Rating)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRatings(ratings: List<Rating>)

    @Query("SELECT * FROM rating WHERE username = :username")
    fun getRatingUsingUsername(username: String): LiveData<List<Rating>>

    @Delete
    fun deleteAll(rating: List<Rating>)
}