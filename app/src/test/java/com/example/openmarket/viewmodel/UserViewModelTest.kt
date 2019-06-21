package com.example.openmarket.viewmodel

import android.app.Application
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.example.openmarket.data.OpenMarketDatabase
import com.example.openmarket.data.User
import org.junit.Test

import org.junit.Assert.*

class UserViewModelTest {
    private lateinit var openMarketDatabase: OpenMarketDatabase
    private lateinit var userViewModel: UserViewModel

    @Test
    fun getUserById() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        openMarketDatabase= Room.inMemoryDatabaseBuilder(context,OpenMarketDatabase::class.java).build()

        userViewModel= UserViewModel(Application())
    }

    @Test
    fun getUserByUsername() {
        var user=User(0,"name","test_username","email","pass","09123456","","")
        userViewModel.insertUser(user)
        var userObject=userViewModel.getUserByUsername("test_username").value as User
        assertEquals(userObject,user)
    }

    @Test
    fun updateUser() {
        var user=User(0,"name","test_username","email","pass","09123456","","")
        userViewModel.insertUser(user)
        user=User(0,"name changed","test_username","email","pass","09123456","","")
        userViewModel.updateUser(user)
        var userObject=userViewModel.getUserByUsername("test_username").value as User
        assertEquals(userObject,user)
    }

    @Test
    fun deleteUser() {
        var user=User(0,"name","test_username","email","pass","09123456","","")
        userViewModel.insertUser(user)
        userViewModel.deleteUser(user)
        var userObject=userViewModel.getUserByUsername("test_username").value
        assertNull(userObject)
    }
}