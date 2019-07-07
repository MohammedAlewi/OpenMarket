package com.example.openmarket.repository

import androidx.lifecycle.LiveData
import com.example.openmarket.MainActivity
import com.example.openmarket.api.OpenMarketApiService
import com.example.openmarket.data.Rating
import com.example.openmarket.data.RatingDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RatingRepository(private val ratingDao: RatingDao) {
    lateinit var activity: MainActivity

    fun getRatingValue(product_id: Long): LiveData<List<Rating>> {
        if (activity.isConnected()) {
            GlobalScope.launch(Dispatchers.IO) {
                val ratings = OpenMarketApiService.getInstance().getAllRatingForProduct(product_id)
                ratingDao.insertRatings(ratings.await().body() ?: emptyList())
            }
        }
        return ratingDao.selectAllRatingForProduct(product_id)
    }

    fun getRatingForUser(username: String): LiveData<List<Rating>> {
        if (activity.isConnected()) {
            GlobalScope.launch(Dispatchers.IO) {
                val ratings = OpenMarketApiService.getInstance().getRating(username)
                var rating = ratings.await().body()
                if (rating != null) ratingDao.insertRating(rating)
            }
        }
        return ratingDao.getRatingUsingUsername(username)
    }

    fun saveRating(rating: Rating) {
        if (activity.isConnected()) {
            GlobalScope.launch(Dispatchers.IO) {
                val ratings = OpenMarketApiService.getInstance().saveRating(rating)
            }
        }
        ratingDao.insertRating(rating)
    }
}