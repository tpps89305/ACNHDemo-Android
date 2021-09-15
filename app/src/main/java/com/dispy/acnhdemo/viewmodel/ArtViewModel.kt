package com.dispy.acnhdemo.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dispy.acnhdemo.model.bean.Art
import com.google.gson.reflect.TypeToken
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArtViewModel : ViewModelBase() {

    private val art: MutableLiveData<List<Art>> by lazy {
        MutableLiveData<List<Art>>().also {
            loadData()
        }
    }

    fun getArt(): LiveData<List<Art>> = art

    private fun loadData() {
        val call: Call<ResponseBody> = acnhService.getArt()
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                val data = response.body()!!.string()
                val listArt = ArrayList<Art>()
                val jsonArt = JSONObject(data)
                for (eachKey in jsonArt.keys()) {
                    val eachObject = jsonArt.optJSONObject(eachKey)!!
                    listArt.add(gson.fromJson(eachObject.toString(), object : TypeToken<Art>() {}.type))
                }
                art.value = listArt
                Log.i("Bugs", "Get bugs success")
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.w("Bugs", "Error when get bugs", t)
            }

        })
    }
}