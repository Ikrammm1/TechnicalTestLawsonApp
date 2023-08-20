package com.lawson.technicaltest.Models

class ModelProduct (
    val Products : List<ListProducts>

) {
    data class ListProducts(
        val product_id: String,
        val productname: String?,
        val price: String?,
        val merchant_name: String?,
        val merchant_id: String?,


        )
}