package com.dispy.acnhdemo.function

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dispy.acnhdemo.bean.Villager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class VillagerViewModel {

    private val acnhService by lazy {
        Retrofit.Builder()
            .baseUrl("http://acnhapi.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ACNHService::class.java)
    }
    private val gson by lazy {
        Gson()
    }
    private val villagers: MutableLiveData<List<Villager>> = MutableLiveData()

    fun getVillagers(): LiveData<List<Villager>> {
        val call: Call<ResponseBody> = acnhService.getVillagers()
        Log.d("Villagers", call.request().toString())
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                val data = response.body()!!.string()
                Log.d("Villagers", data)
                val listVillagers = ArrayList<Villager>()
                val jsonVillagers = JSONObject(data)
                for (eachKey in jsonVillagers.keys()) {
                    val eachObject = jsonVillagers.optJSONObject(eachKey)!!
                    listVillagers.add(gson.fromJson(eachObject.toString(), object : TypeToken<Villager>() {}.type))
                }
                villagers.value = listVillagers
                Log.i("Villagers", "Get villagers success")
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.w("Villagers", "Error when get villagers")
                Log.w("Villagers", t.message!!)
            }

        })
        return villagers
    }

}