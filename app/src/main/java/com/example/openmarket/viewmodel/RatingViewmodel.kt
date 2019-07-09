package com.example.openmarket.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.openmarket.MainActivity
import com.example.openmarket.data.OpenMarketDatabase
import com.example.openmarket.data.Rating
import com.example.openmarket.repository.RatingRepository
import kotlinx.coroutines.*

class RatingViewmodel(application: Application) : AndroidViewModel(application) {
    private lateinit var ratingRepository: RatingRepository

    init {
        var rating_dao = OpenMarketDatabase.getInstance(application).getRatingDao()
        ratingRepository = RatingRepository(rating_dao)
    }

    fun getRatingValue(product_id: Long)=viewModelScope.async(Dispatchers.IO) {
        ratingRepository.getRatingValue(product_id)

    }

    fun getViewersNumber(product_id: Long)=viewModelScope.async(Dispatchers.IO) {
            ratingRepository.getRatingValue(product_id)

    }

    fun saveRating(rating: Rating) = viewModelScope.launch(Dispatchers.IO) {
        ratingRepository.saveRating(rating)
    }

    fun getRatingByUsername(username: String)=viewModelScope.async(Dispatchers.IO){
         ratingRepository.getRatingForUser(username)
    }

    fun getRatingByProductId(product_id: Long)=viewModelScope.async(Dispatchers.IO) {
        ratingRepository.getRatingValue(product_id)
    }

    fun setActivtiy(activity: MainActivity) {
        ratingRepository.activity = activity
    }

}