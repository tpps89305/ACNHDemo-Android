package com.dispy.acnhdemo.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dispy.acnhdemo.model.bean.Fish
import com.google.gson.reflect.TypeToken
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FishesViewModel : ViewModelBase() {

    private val fishes: MutableLiveData<List<Fish>> by lazy {
        MutableLiveData<List<Fish>>().also {
            loadData()
        }
    }

    fun getFishes(): LiveData<List<Fish>> {
        return fishes
    }

    private fun loadData() {
        val call: Call<ResponseBody> = acnhService.getFishes()
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                val data = response.body()!!.string()
                val listFish = ArrayList<Fish>()
                val jsonFishes = JSONObject(data)
                for (eachKey in jsonFishes.keys()) {
                    val eachObject = jsonFishes.optJSONObject(eachKey)!!
                    listFish.add(gson.fromJson(eachObject.toString(), object : TypeToken<Fish>() {}.type))
                }
                fishes.value = listFish
                Log.i("Fishes", "Get fishes success")
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.w("Fishes", "Error when get fishes", t)
            }

        })
    }
}