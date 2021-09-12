package com.dispy.acnhdemo.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dispy.acnhdemo.model.bean.SeaCreature
import com.google.gson.reflect.TypeToken
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SeaCreaturesViewModel : ViewModelBase() {

    private val seaCreatures: MutableLiveData<List<SeaCreature>> by lazy {
        MutableLiveData<List<SeaCreature>>().also {
            loadData()
        }
    }

    fun getSeaCreatures(): LiveData<List<SeaCreature>> {
        return seaCreatures
    }

    fun getSeaCreature(fileName: String): SeaCreature {
        val result = seaCreatures.value?.filter {
            it.fileName == fileName
        }
        return result!!.first()
    }

    private fun loadData() {
        val call: Call<ResponseBody> = acnhService.getSeaCreatures()
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                val data = response.body()!!.string()
                val listSeaCreature = ArrayList<SeaCreature>()
                val jsonSeaCreature = JSONObject(data)
                for (eachKey in jsonSeaCreature.keys()) {
                    val eachObject = jsonSeaCreature.optJSONObject(eachKey)!!
                    listSeaCreature.add(gson.fromJson(eachObject.toString(), object : TypeToken<SeaCreature>() {}.type))
                }
                seaCreatures.value = listSeaCreature
                Log.i("SeaCreature", "Get sea creatures success")
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.w("SeaCreature", "Error when get sea creatures", t)
            }

        })
    }
}