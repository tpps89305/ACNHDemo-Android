package com.dispy.acnhdemo.model

import com.dispy.acnhdemo.model.bean.*
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
    fun getSongs(): Call<SongsMap>

    @Headers("Content-Type: application/json")
    @GET("fish")
    fun getFishes(): Call<FishesMap>

    @Headers("Content-Type: application/json")
    @GET("sea")
    fun getSeaCreatures(): Call<SeaCreaturesMap>

    @Headers("Content-Type: application/json")
    @GET("bugs")
    fun getBugs(): Call<BugsMap>

    @Headers("Content-Type: application/json")
    @GET("fossils")
    fun getFossils(): Call<FossilsMap>

    @Headers("Content-Type: application/json")
    @GET("art")
    fun getArt(): Call<ArtMap>

    @Headers("Content-Type: application/json")
    @GET("backgroundmusic")
    fun getBGM(): Call<BGMsMap>

    @Headers("Content-Type: application/json")
    @GET("houseware")
    fun getHouseware(): Call<HousewaresMap>

    @Headers("Content-Type: application/json")
    @GET("wallmounted")
    fun getWallmounted(): Call<WallmountedsMap>
}