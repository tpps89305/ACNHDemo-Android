package com.dispy.acnhdemo.function

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

abstract class ViewModelBase {

    val acnhService: ACNHService by lazy {
        Retrofit.Builder()
            .baseUrl("http://acnhapi.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ACNHService::class.java)
    }
    val gson by lazy {
        Gson()
    }

}