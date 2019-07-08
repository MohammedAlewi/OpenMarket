package com.example.openmarket.viewmodel.`fake repositories`

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.openmarket.data.Comment
import com.example.openmarket.data.CommentDao
import com.example.openmarket.data.ProductCommentDao
import com.example.openmarket.repository.CommentRepository

class CommentFakeRepository(private val commentDao: CommentDao,private val productCommentDao: ProductCommentDao)
    :CommentRepository(commentDao,productCommentDao){
    var comments:ArrayList<Comment>
    var product_comment_join:HashMap<Long,Long>
    init {
         comments= ArrayList()
        product_comment_join=HashMap()
    }
    override fun insertComment(comment: Comment, product_id: Long) {
        product_comment_join.put(comment.id, product_id)
        comments.add(comment)
    }

    override fun deleteComment(comment: Comment) {
        comments.removeIf { commnt -> commnt.id==comment.id }
    }

    override fun getCommentById(id: Long): LiveData<Comment> {
        var mutableComment=MutableLiveData<Comment>()
        var cmnts=comments.find { comment ->comment.id==id }
        mutableComment.postValue(cmnts)
        return mutableComment
    }

    override fun getCommentByUsername(username: String): LiveData<List<Comment>> {
        var mutableComments=MutableLiveData<List<Comment>>()
        var cmnts=comments.filter { comment ->comment.userName==username }
        mutableComments.postValue(cmnts)
        return mutableComments
    }

    override fun updateComment(comment: Comment) {
        comments.removeIf { commnt -> commnt.id==comment.id }
        comments.add(comment)
    }

    override fun getCommentForProduct(product_id: Long): LiveData<List<Comment>> {
        var mutableComments=MutableLiveData<List<Comment>>()
        var pr_cm=comments.filter { coment -> product_id==product_comment_join.get(coment.id)  }
        mutableComments.postValue(pr_cm)
        return mutableComments
    }
}