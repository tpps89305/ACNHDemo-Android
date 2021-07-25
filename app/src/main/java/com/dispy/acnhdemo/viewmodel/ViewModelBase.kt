package com.dispy.acnhdemo.viewmodel

import androidx.lifecycle.ViewModel
import com.dispy.acnhdemo.model.ACNHService
import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

abstract class ViewModelBase: ViewModel() {

    val acnhService: ACNHService by lazy {
        Retrofit.Builder()
            .baseUrl("https://acnhapi.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ACNHService::class.java)
    }
    val gson by lazy {
        Gson()
    }

}