package com.example.openmarket.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserProductDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUserProduct(userProductJoin: UserProductJoin):Long

    @Query("DELETE FROM UserProductJoin WHERE user_id =:userid")
    fun removeAllRelationByUserId( userid : Long)

    @Query("DELETE FROM UserProductJoin WHERE product_id =:product_id")
    fun removeAllRelationByProductId( product_id : Long)

    @Delete
    fun deleteRelation(userProductJoin: UserProductJoin)

    @Query("SELECT * FROM products INNER JOIN UserProductJoin ON products.product_id=userproductjoin.product_id WHERE userproductjoin.user_id=:user_id ")
    fun getProductsForUser(user_id: Long): LiveData<List<Product>>
}