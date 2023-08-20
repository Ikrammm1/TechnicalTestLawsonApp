package com.lawson.technicaltest.Models

class ModelMerchant(
    val Merchant : List<ListMerchant>

) {
    data class ListMerchant(
        val id: String,
        val merchant_name: String?,
        val address: String?,
        val phone: String?,
        val expired_date: String?,
        val cityname: String?,
        val city_id: String?,

        )
}