package com.example.openmarket.repository

import androidx.lifecycle.LiveData
import com.example.openmarket.data.*

class ProductRepository(private val productDao: ProductDao, private val productCommentDao: ProductCommentDao,
                        private val userProductDao: UserProductDao,private val commentDao: CommentDao){

    fun insertProduct(product: Product,user_id:Long){
        var id=productDao.insertProduct(product)
        userProductDao.insertUserProduct(UserProductJoin(product_id = id,user_id = user_id))
    }

    fun getProductId(id:Long):LiveData<Product>{
        return productDao.getProductById(id)
    }

    fun getProductsByUsername(username:String):LiveData<List<Product>>{
        return productDao.getProductsByUsername(username)
    }


    fun updateProduct(product: Product){
        productDao.updateProduct(product)
    }

    fun deleteProduct(product: Product){
        productCommentDao.removeAllRelationByProductId(product_id = product.id)
        userProductDao.removeAllRelationByProductId(product_id = product.id)
        productDao.deleteComment(product)
    }

    fun getCommentForProduct(product_id:Long):LiveData<List<Comment>>{
        return productCommentDao.getCommentsForProduct(product_id)
    }

    fun getProductsForUser(user_id: Long): LiveData<List<Product>>{
        return userProductDao.getProductsForUser(user_id)
    }
}