package com.example.openmarket.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.openmarket.MainActivity
import com.example.openmarket.data.OpenMarketDatabase
import com.example.openmarket.data.Subscription
import com.example.openmarket.repository.SubscriptionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SubscriptionViewmodel(application: Application): AndroidViewModel(application){
    private lateinit var subscriptionRepository: SubscriptionRepository
    init {
        var subscriptionDao= OpenMarketDatabase.getInstance(application).getSubscriptionDao()
        subscriptionRepository= SubscriptionRepository(subscriptionDao)
    }

    fun saveSubscription(subscription: Subscription)=viewModelScope.launch(Dispatchers.IO){
        subscriptionRepository.saveSubscription(subscription)
    }

    fun getSubscriptionForUser(username:String): LiveData<List<Subscription>> {
        return  subscriptionRepository.getSubscriptionForUser(username)
    }

    fun setActivtiy(activity: MainActivity){
        subscriptionRepository.activity=activity
    }

}