package com.dispy.acnhdemo.model

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface ACNHService {

    @Headers("Content-Type: application/json")
    @GET("villagers")
    fun getVillagers(): Call<ResponseBody>
}