package com.dispy.acnhdemo.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dispy.acnhdemo.model.bean.Fish
import com.dispy.acnhdemo.repository.NetworkRepository
import com.google.gson.reflect.TypeToken
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FishesViewModel : ViewModel() {

    private val fishes: MutableLiveData<List<Fish>> by lazy {
        MutableLiveData<List<Fish>>().also {
            loadData()
        }
    }

    fun getFishes(): LiveData<List<Fish>> {
        return fishes
    }

    fun getFish(fileName: String): Fish {
        val result = fishes.value?.filter {
            it.fileName == fileName
        }
        return result!!.first()
    }

    private fun loadData() {
        val repository = NetworkRepository()
        repository.fetchFishes(object : NetworkRepository.ResponseListener<List<Fish>>{
            override fun onResponse(response: List<Fish>) {
                fishes.value = response
            }
        })
    }
}