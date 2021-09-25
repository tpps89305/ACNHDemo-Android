package com.dispy.acnhdemo.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dispy.acnhdemo.model.bean.Houseware
import com.google.gson.reflect.TypeToken
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HousewaresViewModel : ViewModelBase() {

    private val housewares: MutableLiveData<List<Houseware>> by lazy {
        MutableLiveData<List<Houseware>>().also {
            loadData()
        }
    }

    fun getHousewares(): LiveData<List<Houseware>> = housewares

    private fun loadData() {
        val call: Call<ResponseBody> = acnhService.getHouseware()
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                val data = response.body()!!.string()
                val listHousewares = ArrayList<Houseware>()
                val jsonHousewares = JSONObject(data)
                for (eachKey in jsonHousewares.keys()) {
                    val eachObject = jsonHousewares.optJSONArray(eachKey)!!
                    listHousewares.add(gson.fromJson(eachObject[0].toString(),
                        object : TypeToken<Houseware>() {}.type))
                }
                housewares.value = listHousewares
                Log.i("Housewares", "Get housewares success")
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.w("Housewares", "Error when get housewares", t)
            }

        })
    }

}