package com.lawson.technicaltest.Models

class ModelOrder (
    val Orders : List<ListOrders>

) {
    data class ListOrders(
        val order_id: String,
        val quantity: String?,
        val product_id: String?,
        val productname: String?,
        val price: String?,
        val merchant_name: String?,
        val full_name: String?,
        val user_id: String?,
        val status: String?,
        val total: String?,
        val date: String?,


        )
}