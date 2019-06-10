package com.example.openmarket.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.openmarket.MainActivity
import com.example.openmarket.data.Comment
import com.example.openmarket.data.OpenMarketDatabase
import com.example.openmarket.data.Product
import com.example.openmarket.data.UserProductJoin
import com.example.openmarket.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductViewModel(application: Application,private val activity: MainActivity):AndroidViewModel(application){
    private var productRepository:ProductRepository
    init {
        var product_dao=OpenMarketDatabase.getInstance(application).getProductDao()
        var product_cmnt_dao=OpenMarketDatabase.getInstance(application).getProductCommentDao()
        var product_user_dao=OpenMarketDatabase.getInstance(application).getUserProductDao()
        var comnt_dao=OpenMarketDatabase.getInstance(application).getCommentDao()
        productRepository= ProductRepository(product_dao,product_cmnt_dao,product_user_dao,comnt_dao,activity)
    }
    fun insertProduct(product: Product, user_id:Long)=viewModelScope.launch(Dispatchers.IO){
        productRepository.insertProduct(product,user_id)
    }

    fun getProductId(id:Long): LiveData<Product> {
        return productRepository.getProductId(id)
    }

    fun getProductsByUsername(username:String): LiveData<List<Product>> {
        return productRepository.getProductsByUsername(username)
    }


    fun updateProduct(product: Product)=viewModelScope.launch(Dispatchers.IO){
        productRepository.updateProduct(product)
    }

    fun deleteProduct(product: Product)=viewModelScope.launch(Dispatchers.IO){
        productRepository.deleteProduct(product)
    }

    fun getCommentForProduct(product_id:Long): LiveData<List<Comment>> {
        return productRepository.getCommentForProduct(product_id)
    }

    fun getProductsForUser(user_id: Long): LiveData<List<Product>> {
        return productRepository.getProductsForUser(user_id)
    }
}