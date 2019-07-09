package com.example.openmarket.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {
    @Query("SELECT * FROM users WHERE id =:user_id")
    fun getUserById(user_id: Long): LiveData<User>

    @Query("SELECT * FROM users WHERE username =:username")
    fun getUserByUsername(username: String): LiveData<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User): Long

    @Update
    fun updateUser(user: User)

    @Delete
    fun deleteUser(user: User)

}