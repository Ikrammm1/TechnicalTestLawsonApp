package com.lawson.technicaltest.Models


data class ResponseLogin (
    val status: String,
    var response : Boolean,
    val payload : PayloadLogin
)