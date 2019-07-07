package com.example.openmarket.repository

import androidx.lifecycle.LiveData
import com.example.openmarket.MainActivity
import com.example.openmarket.api.OpenMarketApiService
import com.example.openmarket.data.Rating
import com.example.openmarket.data.Subscription
import com.example.openmarket.data.SubscriptionDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SubscriptionRepository(private val subscriptionDao: SubscriptionDao){
    lateinit var activity: MainActivity

    fun getSubscriptionForUser(username:String): LiveData<List<Subscription>> {
        if (activity.isConnected()){
            GlobalScope.launch(Dispatchers.IO){
                val subscriptions= OpenMarketApiService.getInstance().getSubscriptions(username)
                var result=subscriptions.await().body()
                if (result!=null)subscriptionDao.insertSubscriptions(result)
            }
        }
        return subscriptionDao.getUserSubscriptions(username)
    }

    fun saveSubscription(subscription: Subscription){
        if (activity.isConnected()){
            GlobalScope.launch(Dispatchers.IO){
                val ratings= OpenMarketApiService.getInstance().saveSubscription(subscription)
            }
        }
        subscriptionDao.insertSubscription(subscription)
    }
}