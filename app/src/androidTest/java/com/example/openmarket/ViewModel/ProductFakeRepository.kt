package com.example.openmarket.ViewModel

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.openmarket.api.OpenMarketApiService
import com.example.openmarket.data.*
import com.example.openmarket.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*
import kotlin.random.Random

class ProductFakeRepository (private val productDao: ProductDao, private val productCommentDao: ProductCommentDao,
                             private val userProductDao: UserProductDao, private val commentDao: CommentDao
): ProductRepository(productDao, productCommentDao, userProductDao,commentDao){

    override fun insertProduct(product: Product, user_id:Long){

    }

    override fun getProductId(id:Long): LiveData<Product> {
       return productDao.getProductById(id)
    }

    override fun getProductsByUsername(username:String): LiveData<List<Product>> {
        return productDao.getProductsByUsername(username)
    }

    override fun getAllProducts(): LiveData<List<Product>> {
        return productDao.getAllProduct()
    }


    override fun updateProduct(product: Product){


    }

    override fun deleteProduct(product: Product){

    }

    override fun getCommentForProduct(product_id:Long): LiveData<List<Comment>> {
        return productCommentDao.getCommentsForProduct(product_id)
    }

    override fun getProductsForUser(user_id: Long): LiveData<List<Product>> {
        return userProductDao.getProductsForUser(user_id)
    }
}