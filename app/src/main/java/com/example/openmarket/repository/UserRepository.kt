package com.example.openmarket.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.openmarket.MainActivity
import com.example.openmarket.api.OpenMarketApiService
import com.example.openmarket.data.Product
import com.example.openmarket.data.User
import com.example.openmarket.data.UserDao
import com.example.openmarket.data.UserProductDao
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response

class UserRepository(private val userDao: UserDao, private val userProductDao: UserProductDao) {
    lateinit var activity: MainActivity

     suspend fun getUserById(user_id: Long): LiveData<User> {
        if (activity.isConnected()) {
           // GlobalScope.launch(Dispatchers.IO) {
                val user = OpenMarketApiService.getInstance(activity).findUserById(user_id).await().body() as User
                userDao.insertUser(user)
           // }
        }
        return userDao.getUserById(user_id)
    }

    suspend fun getUserByUsername(username: String): LiveData<User> {
        if (activity.isConnected()) {
            //GlobalScope.launch(Dispatchers.IO) {
                val user = OpenMarketApiService.getInstance(activity).findUserByUsername(username).await().body() as User
                userDao.insertUser(user)
            //}
        }
        return userDao.getUserByUsername(username)
    }

    suspend fun insertUser(user: User) {
        if (activity.isConnected()) {
            //GlobalScope.launch(Dispatchers.IO) {
                var long=OpenMarketApiService.getInstance(activity).registerUser(user).await().body()
                user.id=long?:0
                userDao.insertUser(user)
           // }
        } else {
            var id = userDao.insertUser(user)
            with(activity.getSharedPreferences("unsaved_data_on_server", Context.MODE_PRIVATE).edit()) {
                putLong("user_insert", user.id)
                apply()
            }
        }
    }

    suspend fun updateUser(user: User) {
        if (activity.isConnected()) {
            //GlobalScope.launch(Dispatchers.IO) {
               var long= OpenMarketApiService.getInstance(activity).updateUser(user, user.id).await().body()
                userDao.updateUser(user)
            //}
        } else {
            userDao.updateUser(user)
            with(activity.getSharedPreferences("unsaved_data_on_server", Context.MODE_PRIVATE).edit()) {
                putLong("user_update", user.id)
                apply()
            }
        }
    }

    suspend fun deleteUser(user: User) {
        if (activity.isConnected()) {
           // GlobalScope.launch(Dispatchers.IO) {
                OpenMarketApiService.getInstance(activity).deleteUser(user.id)
                userProductDao.removeAllRelationByUserId(userid = user.id)
                userDao.deleteUser(user)
           // }
        }

    }

    fun login(username: String, password: String): Deferred<Response<User>>? {
        if (activity.isConnected()) {
            return OpenMarketApiService.getInstance(activity).login(username, password)
        }
        return null
    }

    fun getProductsForUser(user_id: Long): LiveData<List<Product>> {
        return userProductDao.getProductsForUser(user_id)
    }

}