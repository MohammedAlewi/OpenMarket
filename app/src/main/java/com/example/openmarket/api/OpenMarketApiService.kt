package com.example.openmarket.api

import com.example.openmarket.data.*
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

interface OpenMarketApiService {

    // users methods ....
    @GET("users/{id}")
    fun findUserById(@Path("id") id:Long):Deferred<Response<User>>

    @GET("users/username/{username}")
    fun findUserByUsername(@Path("username") username:String):Deferred<Response<User>>

    @POST("users")
    fun registerUser(@Body user: User):Deferred<Response<Void>>

    @PUT("users/update/{id}")
    fun updateUser(@Body user: User,@Path("id") id:Long):Deferred<Response<Void>>

    @DELETE("users/delete/{id}")
    fun deleteUser(@Path("id") id:Long):Deferred<Response<Void>>

    @GET("products/users/{user_id}")
    fun getProductsForUser(@Path("user_id") id:Long):Deferred<Response<List<Product>>>


    // product methods
    @POST("products/save/{username}")
    fun saveProduct(@Body product: Product,@Path("username") username: String):Deferred<Response<Void>>

    @POST("products/save_id/{user_id}")
    fun saveProductWithId(@Body product: Product,@Path("user_id") user_id: Long):Deferred<Response<Void>>

    @GET("products/{id}")
    fun getProductId(@Path("id") id:Long):Deferred<Response<Product>>

    @GET("products/username/{username}")
    fun getProductsByUsername(@Path("username") username:String):Deferred<Response<List<Product>>>

    @PUT("products/update/{id}")
    fun updateProduct(@Body product: Product,@Path("id") long: Long):Deferred<Response<Void>>

    @DELETE("products/delete/{id}")
    fun deleteProduct(@Path("id") id: Long):Deferred<Response<Void>>

    @GET("comment/products/{product_id}")
    fun getCommentForProduct(@Path("product_id") product_id:Long):Deferred<Response<List<Comment>>>


    // comment methods.....
    @POST("comment/save/{product_id}")
    fun saveComment(@Body comment: Comment,@Path("product_id") product_id:Long):Deferred<Response<Void>>

    @DELETE("comment/delete/{id}")
    fun deleteComment(@Path("id") id:Long):Deferred<Response<Void>>

    @GET("comment/username/{username}")
    fun getCommentByUsername(@Path("username") username:String):Deferred<Response<List<Comment>>>

    @PUT("comment/update/{id}")
    fun updateComment(@Body comment: Comment,@Path("id") id:Long):Deferred<Response<Void>>

    @GET("comment/{id}")
    fun getCommentById(@Path("id") id:Long):Deferred<Response<Comment>>


    companion object {

        private val url = "http://10.0.2.2:9090/openMarket/api/"

        fun getInstance(): OpenMarketApiService {

//            val interceptor = HttpLoggingInterceptor()
//            interceptor.level = HttpLoggingInterceptor.Level.BASIC
//
//            val client = OkHttpClient
//                .Builder()
//                .addInterceptor(interceptor)
//                .build()

            val retrofit: Retrofit =  Retrofit.Builder()
                .baseUrl(url)
                //.client(client)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()

            return retrofit.create(OpenMarketApiService::class.java)
        }
    }
}