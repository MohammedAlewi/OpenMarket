package com.example.openmarket.viewmodel

import android.app.Application
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.example.openmarket.data.Comment
import com.example.openmarket.data.OpenMarketDatabase
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.util.*


class CommentViewModelTest {
    private lateinit var  openMarketDatabase: OpenMarketDatabase
    private lateinit var commentViewModel: CommentViewModel

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        openMarketDatabase= Room.inMemoryDatabaseBuilder(context,OpenMarketDatabase::class.java).build()

        commentViewModel= CommentViewModel(Application())
    }

    @After
    fun tearDown() {
        openMarketDatabase.close()
    }

    @Test
    fun getCommentForProduct() {
        var comment=Comment(0,"test comment",Date().toString(),"test_username")
        var product_id:Long=12
        commentViewModel.insertComment(comment,product_id)
        var contains=commentViewModel.getCommentForProduct(product_id).value?.contains(comment)
        assert(contains as Boolean)
    }

    @Test
    fun getCommentByUsername() {
        var username="test_username"
        var comment=Comment(0,"test comment",Date().toString(),username)
        var product_id:Long=12
        commentViewModel.insertComment(comment,product_id)
        var contains=commentViewModel.getCommentByUsername(username).value?.contains(comment)
        assert(contains as Boolean)
    }

    @Test
    fun getCommentById() {
        var comment=Comment(0,"test comment",Date().toString(),"test_username")
        var product_id:Long=12
        commentViewModel.insertComment(comment,product_id)
        var comment2=commentViewModel.getCommentById(comment.id).value as Comment
        Assert.assertEquals(comment,comment2)
    }

    @Test
    fun updateComment() {
        var comment=Comment(0,"test comment",Date().toString(),"test_username")
        var product_id:Long=12
        commentViewModel.insertComment(comment,product_id)
        comment=Comment(0,"test comment changed",Date().toString(),"test_username")
        commentViewModel.updateComment(comment)
        var old_comment=commentViewModel.getCommentById(comment.id).value as Comment
        Assert.assertEquals(comment,old_comment)
    }


}