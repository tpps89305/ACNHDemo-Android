package com.dispy.acnhdemo.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dispy.acnhdemo.model.bean.Wallmounted
import com.google.gson.reflect.TypeToken
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WallmountedViewModel : ViewModelBase() {

    private val wallmounteds: MutableLiveData<List<Wallmounted>> by lazy {
        MutableLiveData<List<Wallmounted>>().also {
            loadData()
        }
    }

    fun getWallmounteds(): LiveData<List<Wallmounted>> = wallmounteds

    fun getWallmounted(fileName: String): Wallmounted {
        val result = wallmounteds.value?.filter {
            it.fileName == fileName
        }
        return result!!.first()
    }

    private fun loadData() {
        val call: Call<ResponseBody> = acnhService.getWallmounted()
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                val data = response.body()!!.string()
                val listWallmounted = ArrayList<Wallmounted>()
                val jsonWallmounted = JSONObject(data)
                for (eachKey in jsonWallmounted.keys()) {
                    val eachObject = jsonWallmounted.optJSONArray(eachKey)!!
                    listWallmounted.add(
                        gson.fromJson(
                            eachObject[0].toString(),
                            object : TypeToken<Wallmounted>() {}.type
                        )
                    )
                }
                wallmounteds.value = listWallmounted
                Log.i("Wallmounted", "Get wallmounted success")
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.w("Wallmounted", "Error when get wallmounted", t)
            }

        })
    }

}