package com.example.openmarket.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ProductDao {
    @Query("SELECT * FROM products WHERE product_id =:product_id")
    fun getProductById(product_id:Long):LiveData<Product>

    @Query("SELECT * FROM products ")
    fun getAllProduct():LiveData<List<Product>>

    @Query("SELECT * FROM products WHERE user_name =:username")
    fun getProductsByUsername(username:String):LiveData<List<Product>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProduct(product: Product):Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProducts(products: List<Product>)

    @Update
    fun updateProduct(product: Product)

    @Delete
    fun deleteComment(product: Product)

}