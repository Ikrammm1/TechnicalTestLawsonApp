package com.lawson.technicaltest.Models

class ModelCity (
    val City : List<ListCity>

) {
    data class ListCity(
        val id: String,
        val name: String?,
        val latitude: String?,
        val longitude: String?

        )
}