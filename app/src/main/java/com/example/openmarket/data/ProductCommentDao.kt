package com.example.openmarket.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ProductCommentDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertProductComment(productCommentJoin: ProductCommentJoin):Long

    @Query("DELETE FROM ProductCommentJoin WHERE comment_id =:comment_id")
    fun removeAllRelationByCommentId( comment_id : Long)

    @Query("DELETE FROM ProductCommentJoin WHERE product_id =:product_id")
    fun removeAllRelationByProductId( product_id : Long)

    @Delete
    fun deleteRelation(productCommentJoin: ProductCommentJoin)

    @Query("SELECT * FROM comments INNER JOIN ProductCommentJoin ON comments.id=productcommentjoin.comment_id WHERE productcommentjoin.product_id=:product_id ")
    fun getCommentsForProduct(product_id: Long): LiveData<List<Comment>>

}