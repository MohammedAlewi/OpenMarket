package com.example.openmarket.data

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Comment::class,Product::class,User::class,
    ProductCommentJoin::class,UserProductJoin::class,Rating::class,Subscription::class),version = 1)
abstract class OpenMarketDatabase:RoomDatabase(){
    abstract fun getProductDao():ProductDao
    abstract fun getUserDao():UserDao
    abstract fun getCommentDao():CommentDao
    abstract fun getProductCommentDao():ProductCommentDao
    abstract fun getUserProductDao():UserProductDao
    abstract fun getRatingDao():RatingDao
    abstract fun getSubscriptionDao():SubscriptionDao

    companion object{
        @Volatile
        var INSTANCE:OpenMarketDatabase?=null

        fun getInstance(context: Context):OpenMarketDatabase{
            var temp= INSTANCE
            if(temp!=null){
                return temp
            }
            synchronized(this){
                var db= Room.databaseBuilder(
                    context.applicationContext,
                    OpenMarketDatabase::class.java,
                    "open_market_database"
                ).build()

                INSTANCE=db
                return db
            }
        }

    }
}