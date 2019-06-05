package com.example.openmarket.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.openmarket.data.OpenMarketDatabase
import com.example.openmarket.data.Product
import com.example.openmarket.data.User
import com.example.openmarket.repository.UserRepository

class UserViewModel(application: Application):AndroidViewModel(application){
    private  var userRepository:UserRepository
    init {
        var user_dao=OpenMarketDatabase.getInstance(application).getUserDao()
        var user_prod_dao=OpenMarketDatabase.getInstance(application).getUserProductDao()
        userRepository=UserRepository(user_dao,user_prod_dao)
    }

    fun getUserById(user_id:Long): LiveData<User> {
        return userRepository.getUserById(user_id)
    }

    fun getUserByUsername(username:String): LiveData<User> {
        return userRepository.getUserByUsername(username)
    }

    fun insertUser(user: User){
        userRepository.insertUser(user)
    }

    fun updateUser(user: User){
        userRepository.updateUser(user)
    }

    fun deleteUser(user: User){
        userRepository.deleteUser(user)
    }

    fun getProductsForUser(user_id: Long): LiveData<List<Product>> {
        return userRepository.getProductsForUser(user_id)
    }
}