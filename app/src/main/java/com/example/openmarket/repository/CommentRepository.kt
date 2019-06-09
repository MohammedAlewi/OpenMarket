package com.example.openmarket.repository

import android.content.Context
import android.os.AsyncTask
import androidx.annotation.WorkerThread
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.openmarket.MainActivity
import com.example.openmarket.api.OpenMarketApiService
import com.example.openmarket.data.Comment
import com.example.openmarket.data.CommentDao
import com.example.openmarket.data.ProductCommentDao
import com.example.openmarket.data.ProductCommentJoin
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.*
import kotlin.random.Random

class CommentRepository(private val commentDao: CommentDao,private val productCommentDao: ProductCommentDao){
    lateinit var activity: MainActivity
    @WorkerThread
    fun insertComment(comment: Comment,product_id:Long){
        if (activity.isConnected()){
            GlobalScope.launch(Dispatchers.IO){
                val id=OpenMarketApiService.getInstance().saveComment(comment,product_id).await().body() as Long
                comment.id=id
                commentDao.insertComment(comment)
                productCommentDao.insertProductComment(ProductCommentJoin(comment_id = id,product_id = product_id))
            }
        }else{
            val id= Random(Date().time).nextLong()
            comment.id=id
            commentDao.insertComment(comment)
            productCommentDao.insertProductComment(ProductCommentJoin(comment_id = id,product_id = product_id))
            with(activity.getSharedPreferences("unsaved_data_on_server",Context.MODE_PRIVATE).edit()){
                putLong("comment_insert",id)
                apply()
            }
        }
    }


    fun deleteComment(comment: Comment){
        if (activity.isConnected()){
            GlobalScope.launch(Dispatchers.IO){
                OpenMarketApiService.getInstance().deleteComment(comment.id)
                productCommentDao.removeAllRelationByCommentId(comment.id)
                commentDao.deleteComment(comment)
            }
        }else{
            productCommentDao.removeAllRelationByCommentId(comment.id)
            commentDao.deleteComment(comment)
            with(activity.getSharedPreferences("unsaved_data_on_server",Context.MODE_PRIVATE).edit()){
                putLong("comment_delete",comment.id)
                commit()
            }
        }
    }

    fun getCommentForProduct(product_id:Long):LiveData<List<Comment>>{
        //val commentsVal=    MutableLiveData<List<Comment>>
        if (activity.isConnected()){
            GlobalScope.launch(Dispatchers.IO){
                val comments= OpenMarketApiService.getInstance().getCommentForProduct(product_id).await().body() as List<Comment>
                commentDao.insertComments(comments)
            }
        }
        return productCommentDao.getCommentsForProduct(product_id)

    }

    fun getCommentByUsername(username:String):LiveData<List<Comment>>{
        if (activity.isConnected()){
            GlobalScope.launch(Dispatchers.IO){
                val comments= OpenMarketApiService.getInstance().getCommentByUsername(username).await().body() as List<Comment>
                commentDao.insertComments(comments)
            }
        }
        return commentDao.getCommentByUsername(username)


    }

    fun updateComment(comment: Comment){
        if (activity.isConnected()) {
            GlobalScope.launch(Dispatchers.IO) {
                OpenMarketApiService.getInstance().updateComment(comment, comment.id)
                commentDao.updateComment(comment)
            }
        }
    }

    fun getCommentById(id:Long):LiveData<Comment>{
        if (activity.isConnected()){
            GlobalScope.launch(Dispatchers.IO){
                val comments= OpenMarketApiService.getInstance().getCommentById(id).await().body() as Comment
                commentDao.insertComment(comments)
            }
        }
        return commentDao.getCommentById(id)
    }

}

