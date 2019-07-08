package com.example.openmarket.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.openmarket.MainActivity
import com.example.openmarket.api.OpenMarketApiService
import com.example.openmarket.data.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ProductRepository(
    private val productDao: ProductDao, private val productCommentDao: ProductCommentDao,
    private val userProductDao: UserProductDao, private val commentDao: CommentDao
) {
    lateinit var activity: MainActivity
    fun insertProduct(product: Product, user_id: Long) {
        if (activity.isConnected()) {
            GlobalScope.launch(Dispatchers.IO) {
                val id = OpenMarketApiService.getInstance(activity).saveProduct(product,product.userName).await().body() as Long
                product.id = id
                productDao.insertProduct(product)
                userProductDao.insertUserProduct(UserProductJoin(product_id = id, user_id = user_id))
            }
        } else {
            var product_id = productDao.insertProduct(product)
            userProductDao.insertUserProduct(UserProductJoin(product_id = product_id, user_id = user_id))
            with(activity.getSharedPreferences("unsaved_data_on_server", Context.MODE_PRIVATE).edit()) {
                putLong("product_insert", product_id)
                apply()
            }
        }
    }

    fun getProductId(id: Long): LiveData<Product> {
        if (activity.isConnected()) {
            GlobalScope.launch(Dispatchers.IO) {
                val products = OpenMarketApiService.getInstance(activity).getProductId(id).await().body() as Product
                productDao.insertProduct(products)
            }
        }
        return productDao.getProductById(id)
    }

    fun getProductsByUsername(username: String): LiveData<List<Product>> {
        if (activity.isConnected()) {
            GlobalScope.launch(Dispatchers.IO) {
                val products =
                    OpenMarketApiService.getInstance(activity).getCommentByUsername(username).await().body() as List<Product>
                productDao.insertProducts(products)
            }
        }
        return productDao.getProductsByUsername(username)
    }

    fun getAllProducts(): LiveData<List<Product>> {
        if (activity.isConnected()) {
            GlobalScope.launch(Dispatchers.IO) {
                val products = OpenMarketApiService.getInstance(activity).getAllProducts().await().body() as List<Product>
                productDao.insertProducts(products)
            }
        }
        return productDao.getAllProduct()
    }


    fun updateProduct(product: Product) {
        if (activity.isConnected()) {
            GlobalScope.launch(Dispatchers.IO) {
                OpenMarketApiService.getInstance(activity).updateProduct(product, product.id)
                productDao.updateProduct(product)
            }
        } else {
            productDao.updateProduct(product)
            with(activity.getSharedPreferences("unsaved_data_on_server", Context.MODE_PRIVATE).edit()) {
                putLong("product_update", product.id)
                apply()
            }
        }

    }

    fun deleteProduct(product: Product) {
        if (activity.isConnected()) {
            GlobalScope.launch(Dispatchers.IO) {
                OpenMarketApiService.getInstance(activity).deleteProduct(product.id)

                productCommentDao.removeAllRelationByProductId(product_id = product.id)
                userProductDao.removeAllRelationByProductId(product_id = product.id)
                productDao.deleteComment(product)
            }
        } else {
            var product_id = productDao.insertProduct(product)
            productDao.deleteComment(product)
            with(activity.getSharedPreferences("unsaved_data_on_server", Context.MODE_PRIVATE).edit()) {
                putLong("product_delete", product_id)
                apply()
            }
        }
    }

    fun getCommentForProduct(product_id: Long): LiveData<List<Comment>> {
        if (activity.isConnected()) {
            GlobalScope.launch(Dispatchers.IO) {
                val comments =
                    OpenMarketApiService.getInstance(activity).getCommentForProduct(product_id).await().body() as List<Comment>
                commentDao.insertComments(comments)
            }
        }
        return productCommentDao.getCommentsForProduct(product_id)
    }

    fun getProductsForUser(user_id: Long): LiveData<List<Product>> {
        if (activity.isConnected()) {
            GlobalScope.launch(Dispatchers.IO) {
                val products =
                    OpenMarketApiService.getInstance(activity).getProductsForUser(user_id).await().body() as List<Product>
                productDao.insertProducts(products)
            }
        }
        return userProductDao.getProductsForUser(user_id)
    }
}