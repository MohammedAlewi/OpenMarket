package com.example.openmarket.viewmodel.`fake repositories`

import androidx.lifecycle.LiveData
import com.example.openmarket.data.Comment
import com.example.openmarket.data.CommentDao
import com.example.openmarket.data.ProductCommentDao
import com.example.openmarket.repository.CommentRepository

class CommentFakeRepository(private val commentDao: CommentDao,private val productCommentDao: ProductCommentDao)
    :CommentRepository(commentDao,productCommentDao){

    override fun insertComment(comment: Comment, product_id: Long) {
        super.insertComment(comment, product_id)
    }

    override fun deleteComment(comment: Comment) {
        super.deleteComment(comment)
    }

    override fun getCommentById(id: Long): LiveData<Comment> {
        return super.getCommentById(id)
    }

    override fun getCommentByUsername(username: String): LiveData<List<Comment>> {
        return super.getCommentByUsername(username)
    }

    override fun updateComment(comment: Comment) {
        super.updateComment(comment)
    }

    override fun getCommentForProduct(product_id: Long): LiveData<List<Comment>> {
        return super.getCommentForProduct(product_id)
    }
}