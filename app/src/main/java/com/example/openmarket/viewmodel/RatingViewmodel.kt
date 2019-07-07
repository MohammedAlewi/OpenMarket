package com.example.openmarket.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.openmarket.MainActivity
import com.example.openmarket.data.OpenMarketDatabase
import com.example.openmarket.data.Rating
import com.example.openmarket.repository.RatingRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RatingViewmodel(application: Application) : AndroidViewModel(application) {
    private lateinit var ratingRepository: RatingRepository

    init {
        var rating_dao = OpenMarketDatabase.getInstance(application).getRatingDao()
        ratingRepository = RatingRepository(rating_dao)
    }

    fun getRatingValue(product_id: Long): LiveData<Double> {
        var result = MutableLiveData<Double>()
        GlobalScope.launch(Dispatchers.Main) {
            ratingRepository.getRatingValue(product_id).observe(ratingRepository.activity, Observer { ratings ->
                ratings.let {
                    var rate = 0.0
                    ratings.forEach { rate += it.rateNo }
                    result.postValue(rate / ratings.size)
                }
            })
        }
        return result
    }

    fun getViewersNumber(product_id: Long): LiveData<Int> {
        var result = MutableLiveData<Int>()
        GlobalScope.launch(Dispatchers.Main) {
            ratingRepository.getRatingValue(product_id).observe(ratingRepository.activity, Observer { ratings ->
                ratings.let { result.postValue(ratings.size) }
            })
        }
        return result
    }

    fun saveRating(rating: Rating) = viewModelScope.launch(Dispatchers.IO) {
        ratingRepository.saveRating(rating)
    }

    fun getRatingByUsername(username: String): LiveData<List<Rating>> {
        return ratingRepository.getRatingForUser(username)
    }

    fun getRatingByProductId(product_id: Long): LiveData<List<Rating>> {
        return ratingRepository.getRatingValue(product_id)
    }

    fun setActivtiy(activity: MainActivity) {
        ratingRepository.activity = activity
    }

}