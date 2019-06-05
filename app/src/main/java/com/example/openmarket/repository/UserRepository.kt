package com.example.openmarket.repository

import androidx.lifecycle.LiveData
import com.example.openmarket.data.Product
import com.example.openmarket.data.User
import com.example.openmarket.data.UserDao
import com.example.openmarket.data.UserProductDao

class UserRepository (private val userDao: UserDao,private val userProductDao: UserProductDao){
    fun getUserById(user_id:Long): LiveData<User>{
        return userDao.getUserById(user_id)
    }

    fun getUserByUsername(username:String): LiveData<User>{
        return userDao.getUserByUsername(username)
    }

    fun insertUser(user: User){
        userDao.insertUser(user)
    }

    fun updateUser(user: User){
        userDao.updateUser(user)
    }

    fun deleteUser(user: User){
        userProductDao.removeAllRelationByUserId(userid = user.id)
        userDao.deleteUser(user)
    }

    fun getProductsForUser(user_id: Long): LiveData<List<Product>>{
        return userProductDao.getProductsForUser(user_id)
    }
}