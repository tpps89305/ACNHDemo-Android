package com.dispy.acnhdemo.model

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface ACNHService {

    @Headers("Content-Type: application/json")
    @GET("villagers")
    fun getVillagers(): Call<ResponseBody>

    @Headers("Content-Type: application/json")
    @GET("songs")
    fun getSongs(): Call<ResponseBody>

    @Headers("Content-Type: application/json")
    @GET("fish")
    fun getFishes(): Call<ResponseBody>

    @Headers("Content-Type: application/json")
    @GET("sea")
    fun getSeaCreatures(): Call<ResponseBody>

    @Headers("Content-Type: application/json")
    @GET("bugs")
    fun getBugs(): Call<ResponseBody>

    @Headers("Content-Type: application/json")
    @GET("fossils")
    fun getFossils(): Call<ResponseBody>

    @Headers("Content-Type: application/json")
    @GET("art")
    fun getArt(): Call<ResponseBody>

    @Headers("Content-Type: application/json")
    @GET("backgroundmusic")
    fun getBGM(): Call<ResponseBody>
}