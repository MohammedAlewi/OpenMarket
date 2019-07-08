package com.example.openmarket.ViewModel

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.openmarket.MainActivity
import com.example.openmarket.api.OpenMarketApiService
import com.example.openmarket.data.Product
import com.example.openmarket.data.User
import com.example.openmarket.data.UserDao
import com.example.openmarket.data.UserProductDao
import com.example.openmarket.repository.UserRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response

class UserFakeRepository(private val userDao: UserDao, private val userProductDao: UserProductDao) : UserRepository (userDao, userProductDao){

   override fun getUserById(user_id:Long): LiveData<User> {

        return userDao.getUserById(user_id)
    }

   override fun getUserByUsername(username:String): LiveData<User> {

        return userDao.getUserByUsername(username)
    }

    override fun insertUser(user: User){

    }

    override fun updateUser(user: User){

    }

    override fun deleteUser(user: User){


    }

    override fun login(username: String,password:String): Deferred<Response<Void>>? {

        return null
    }

    override fun getProductsForUser(user_id: Long): LiveData<List<Product>> {
        return userProductDao.getProductsForUser(user_id)
    }

}