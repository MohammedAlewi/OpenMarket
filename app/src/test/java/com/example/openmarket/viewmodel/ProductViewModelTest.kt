package com.example.openmarket.viewmodel

import android.app.Application
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.example.openmarket.data.Comment
import com.example.openmarket.data.OpenMarketDatabase
import com.example.openmarket.data.Product
import org.junit.Test

import org.junit.Assert.*
import java.util.*

class ProductViewModelTest {
    private lateinit var productViewModel: ProductViewModel
    private lateinit var openMarketDatabase: OpenMarketDatabase

    @Test
    fun getProductId() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        openMarketDatabase= Room.inMemoryDatabaseBuilder(context,OpenMarketDatabase::class.java).build()

        productViewModel= ProductViewModel(Application())
    }

    @Test
    fun getProductsByUsername() {
        var username="test_username"
        var user_id:Long=12
        var product=Product(0,"","test","car","this is test product",1,1.0,Date().toString(),username)
        productViewModel.insertProduct(product,user_id)
        var contains=productViewModel.getProductsByUsername(username).value  as Product
        assertEquals(contains,product)
    }

    @Test
    fun updateProduct() {
        var username="test_username"
        var user_id:Long=12
        var product=Product(0,"","test","car","this is test product",1,1.0,Date().toString(),username)
        productViewModel.insertProduct(product,user_id)
        product=Product(0,"","test product changed","car","this is test product",1,1.0,Date().toString(),username)
        productViewModel.updateProduct(product)
        var new_product=productViewModel.getProductsByUsername(username).value  as Product
        assertEquals(new_product,product)
    }

    @Test
    fun getProductsForUser() {
        var username="test_username"
        var user_id:Long=12
        var product=Product(0,"","test","car","this is test product",1,1.0,Date().toString(),username)
        productViewModel.insertProduct(product,user_id)
        var contains=productViewModel.getProductsForUser(user_id).value  as Product
        assertEquals(contains,product)
    }
}