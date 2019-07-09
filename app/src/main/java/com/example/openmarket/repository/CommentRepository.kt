package com.example.openmarket.repository

import android.content.Context
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.openmarket.MainActivity
import com.example.openmarket.api.OpenMarketApiService
import com.example.openmarket.data.Comment
import com.example.openmarket.data.CommentDao
import com.example.openmarket.data.ProductCommentDao
import com.example.openmarket.data.ProductCommentJoin
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*
import kotlin.random.Random

open class CommentRepository(private val commentDao: CommentDao, private val productCommentDao: ProductCommentDao) {
    lateinit var activity: MainActivity
    @WorkerThread
    suspend open fun insertComment(comment: Comment, product_id: Long) {
        if (activity.isConnected()) {
                val id = OpenMarketApiService.getInstance(activity).saveComment(comment, product_id).await().body() as Long
                comment.id = id
                commentDao.insertComment(comment)
                productCommentDao.insertProductComment(ProductCommentJoin(comment_id = id, product_id = product_id))
        } else {
            val id = Random(Date().time).nextLong()
            comment.id = id
            commentDao.insertComment(comment)
            productCommentDao.insertProductComment(ProductCommentJoin(comment_id = id, product_id = product_id))
            with(activity.getSharedPreferences("unsaved_data_on_server", Context.MODE_PRIVATE).edit()) {
                putLong("comment_insert", id)
                apply()
            }
        }
    }


    suspend open fun deleteComment(comment: Comment) {
        if (activity.isConnected()) {
                OpenMarketApiService.getInstance(activity).deleteComment(comment.id)
                productCommentDao.removeAllRelationByCommentId(comment.id)
                commentDao.deleteComment(comment)
        } else {
            productCommentDao.removeAllRelationByCommentId(comment.id)
            commentDao.deleteComment(comment)
            with(activity.getSharedPreferences("unsaved_data_on_server", Context.MODE_PRIVATE).edit()) {
                putLong("comment_delete", comment.id)
                commit()
            }
        }
    }

    suspend open fun getCommentForProduct(product_id: Long): LiveData<List<Comment>> {
        //val commentsVal=    MutableLiveData<List<Comment>>
        if (activity.isConnected()) {
                val comments =
                    OpenMarketApiService.getInstance(activity).getCommentForProduct(product_id).await().body() as List<Comment>
                commentDao.insertComments(comments)
        }
        return productCommentDao.getCommentsForProduct(product_id)

    }

    suspend open fun getCommentByUsername(username: String): LiveData<List<Comment>> {
        if (activity.isConnected()) {
                val comments =
                    OpenMarketApiService.getInstance(activity).getCommentByUsername(username).await().body() as List<Comment>
                commentDao.insertComments(comments)
        }
        return commentDao.getCommentByUsername(username)


    }

    suspend open fun updateComment(comment: Comment) {
        if (activity.isConnected()) {
                OpenMarketApiService.getInstance(activity).updateComment(comment, comment.id)
                commentDao.updateComment(comment)
        }
    }

    suspend open fun getCommentById(id: Long): LiveData<Comment> {
        if (activity.isConnected()) {
                val comments = OpenMarketApiService.getInstance(activity).getCommentById(id).await().body() as Comment
                commentDao.insertComment(comments)
        }
        return commentDao.getCommentById(id)
    }

}

