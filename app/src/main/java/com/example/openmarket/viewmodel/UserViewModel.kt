package com.example.openmarket.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.openmarket.MainActivity
import com.example.openmarket.data.OpenMarketDatabase
import com.example.openmarket.data.Product
import com.example.openmarket.data.User
import com.example.openmarket.repository.UserRepository
import kotlinx.coroutines.*

class UserViewModel(application: Application) : AndroidViewModel(application) {
    private var userRepository: UserRepository
    lateinit var userObject:LiveData<User>

    init {
        var user_dao = OpenMarketDatabase.getInstance(application).getUserDao()
        var user_prod_dao = OpenMarketDatabase.getInstance(application).getUserProductDao()
        userRepository = UserRepository(user_dao, user_prod_dao)
    }

    fun getUserById(user_id: Long): LiveData<User> {
        return userRepository.getUserById(user_id)
    }

    fun setActivtiy(activity: MainActivity) {
        userRepository.activity = activity
    }

    fun getUserByUsername(username: String): LiveData<User> {
        var usr= userRepository.getUserByUsername(username)
        userObject=usr
        return usr
    }

    fun insertUser(user: User) = viewModelScope.launch(Dispatchers.IO) {
        userRepository.insertUser(user)
    }

    fun updateUser(user: User) = viewModelScope.launch(Dispatchers.IO) {
        userRepository.updateUser(user)
    }

    fun deleteUser(user: User) = viewModelScope.launch(Dispatchers.IO) {
        userRepository.deleteUser(user)
    }

    fun getProductsForUser(user_id: Long): LiveData<List<Product>> {
        return userRepository.getProductsForUser(user_id)
    }

     fun login(username: String, password: String): Boolean? {
         val login= userRepository.login(username, password)
         var response=true
         var result= viewModelScope.async(Dispatchers.IO) {
            val code = login?.await()
            println("code.............$code...... CD: ${code?.code()} URL $code?.url SCC: $code?.isSuccessful Err: $code?.errorBody() ")
             code?.toString()
         }
         runBlocking {
             response=result.await()?.contains("/login?error") ?:false
             response=!response
         }
         println("..................$response ")
         return response
    }
}