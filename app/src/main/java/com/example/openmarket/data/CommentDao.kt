package com.example.openmarket.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CommentDao {
    @Query("SELECT * FROM comments WHERE id =:comment_id")
    fun getCommentById(comment_id: Long): LiveData<Comment>

    @Query("SELECT * FROM comments WHERE user_name =:username")
    fun getCommentByUsername(username: String): LiveData<List<Comment>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertComment(comment: Comment): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertComments(comments: List<Comment>)

    @Delete
    fun deleteComment(comment: Comment)

    @Update
    fun updateComment(comment: Comment)
}