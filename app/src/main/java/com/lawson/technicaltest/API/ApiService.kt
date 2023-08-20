package com.lawson.technicaltest.API

import com.lawson.technicaltest.Models.*
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @FormUrlEncoded
    @POST("login.php")
    fun login(
        @Field("email") email : String
    ) : Call<ResponseLogin>

    @FormUrlEncoded
    @POST("register.php")
    fun Register(
        @Field("email") email : String,
        @Field("full_name") full_name : String,
        @Field("address") address : String,
        @Field("phone") phone : String,
        @Field("date_of_birth") date_of_birth : String,
    ) : Call<Response>

    @GET("GetCity.php")
    fun GetCity():Call<ModelCity>

    @FormUrlEncoded
    @POST("AddCity.php")
    fun AddCity(
        @Field("name") name : String,
        @Field("latitude") latitude : String,
        @Field("longitude") longitude : String,
    ) : Call<Response>

    @FormUrlEncoded
    @POST("UpdateCity.php")
    fun UpdateCity(
        @Field("id") id : String,
        @Field("name") name : String,
        @Field("latitude") latitude : String,
        @Field("longitude") longitude : String,
    ) : Call<Response>

    @FormUrlEncoded
    @POST("DeleteCity.php")
    fun DeleteCity(
        @Field("id") id : String,

    ) : Call<Response>

    @GET("GetMerchant.php")
    fun GetMerchant():Call<ModelMerchant>

    @FormUrlEncoded
    @POST("AddMerchant.php")
    fun AddMerchant(
        @Field("merchant_name") merchant_name : String,
        @Field("city_id") city_id : String,
        @Field("phone") phone : String,
        @Field("address") address : String,
        @Field("expired_date") expired_date : String,
    ) : Call<Response>

    @FormUrlEncoded
    @POST("UpdateMerchant.php")
    fun UpdateMerchant(
        @Field("id") id : String,
        @Field("merchant_name") merchant_name : String,
        @Field("city_id") city_id : String,
        @Field("phone") phone : String,
        @Field("address") address : String,
        @Field("expired_date") expired_date : String,
    ) : Call<Response>

    @FormUrlEncoded
    @POST("DeleteMerchant.php")
    fun DeleteMerchant(
        @Field("id") id : String
        ) : Call<Response>

    @GET("GetProducts.php")
    fun GetProducts():Call<ModelProduct>

    @FormUrlEncoded
    @POST("AddProduct.php")
    fun AddProduct(
        @Field("name") name : String,
        @Field("merchant_id") merchant_id : String,
        @Field("price") price : String,
    ) : Call<Response>

    @FormUrlEncoded
    @POST("UpdateProduct.php")
    fun UpdateProduct(
        @Field("product_id") product_id : String,
        @Field("name") name : String,
        @Field("merchant_id") merchant_id : String,
        @Field("price") price : String,
    ) : Call<Response>

    @FormUrlEncoded
    @POST("DeleteProduct.php")
    fun DeleteProduct(
        @Field("product_id") product_id : String,
        ) : Call<Response>

    @GET("GetOrders.php")
    fun GetOrders():Call<ModelOrder>

    @FormUrlEncoded
    @POST("AddOrder.php")
    fun AddOrder(
        @Field("quantity") quantity : String,
        @Field("product_id") product_id : String,
        @Field("user_id") user_id : String,
    ) : Call<Response>

    @FormUrlEncoded
    @POST("UpdateOrder.php")
    fun UpdateOrder(
        @Field("order_id") order_id : String,
        @Field("quantity") quantity : String,
        @Field("product_id") product_id : String,
        @Field("user_id") user_id : String,
    ) : Call<Response>

    @FormUrlEncoded
    @POST("DeleteOrder.php")
    fun DeleteOrder(
        @Field("order_id") order_id : String,
    ) : Call<Response>


}