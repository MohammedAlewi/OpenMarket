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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {
    private var userRepository: UserRepository

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
        return userRepository.getUserByUsername(username)
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
        var login = userRepository.login(username, password)
        if (login == null)
            return null
        var result: Boolean? = null
        GlobalScope.launch(Dispatchers.Main) {
            var code = login.await()
            if (code.code() == 301)
                result = true
            result = false
        }
        return result
    }
}