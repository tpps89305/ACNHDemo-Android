package com.dispy.acnhdemo.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dispy.acnhdemo.model.bean.Villager
import com.google.gson.reflect.TypeToken
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VillagerViewModel: ViewModelBase() {

    private val villagers: MutableLiveData<List<Villager>> by lazy {
        MutableLiveData<List<Villager>>().also {
            loadVillagers()
        }
    }

    fun getVillagers(): LiveData<List<Villager>> {
        return villagers
    }

    private fun loadVillagers() {
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
                villagers.value = ArrayList()
            }

        })
    }

}