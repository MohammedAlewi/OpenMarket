package com.example.openmarket.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.openmarket.data.Comment
import com.example.openmarket.data.CommentDao
import com.example.openmarket.data.ProductCommentDao
import com.example.openmarket.data.ProductCommentJoin

class CommentRepository(private val commentDao: CommentDao,private val productCommentDao: ProductCommentDao){

    @WorkerThread
    fun insertComment(comment: Comment,product_id:Long){
        val id=commentDao.insertComment(comment)
        productCommentDao.insertProductComment(ProductCommentJoin(comment_id = id,product_id = product_id))
    }


    fun deleteComment(comment: Comment){
        productCommentDao.removeAllRelationByCommentId(comment.id)
        commentDao.deleteComment(comment)
    }

    fun getCommentForProduct(product_id:Long):LiveData<List<Comment>>{
        return productCommentDao.getCommentsForProduct(product_id)
    }

    fun getCommentByUsername(username:String):LiveData<List<Comment>>{
        return commentDao.getCommentByUsername(username)
    }

    fun updateComment(comment: Comment)=commentDao.updateComment(comment)

    fun getCommentById(id:Long):LiveData<Comment>{
        return commentDao.getCommentById(id)
    }
}