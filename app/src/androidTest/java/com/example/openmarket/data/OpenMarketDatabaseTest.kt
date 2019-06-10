package com.example.openmarket.data


import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers.equalTo
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class OpenMarketDatabaseTest{
    private lateinit var userDao: UserDao
    private lateinit var commentDao: CommentDao
    private lateinit var productDao: ProductDao
    private lateinit var db: OpenMarketDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, OpenMarketDatabase::class.java).build()
        userDao = db.getUserDao()
        commentDao=db.getCommentDao()
        productDao=db.getProductDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeUserAndReadInList() {
        val user: User = User (
            0,"fullName","username","email",
                "password","phoneNo","pictureId","locationId"
        )
        userDao.insertUser(user)
        val byName:User? = userDao.getUserByUsername("username").value
        assertThat(byName?.username, equalTo(user.username))
    }

    @Test
    @Throws(Exception::class)
    fun writeCommentAndReadInList(){
        val comment:Comment=Comment(
            -1,"comment_data","comment_date","user_name"
        )
        commentDao.insertComment(comment)
        val fromDb=commentDao.getCommentById(-1).value
        assertThat(comment.userName, equalTo(fromDb?.userName))
    }

    @Test
    @Throws(Exception::class)
    fun writeProductAndReadInList(){
        val product:Product=Product(
            -2,"product_image","product_name",
            "product_type","product_description",
            0,0.0,"product_date",
            "user_name"
        )
        productDao.insertProduct(product)
        val fromDb=productDao.getProductById(-2).value
        assertThat(product.userName, equalTo(fromDb?.userName))
    }
}