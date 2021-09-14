package com.dispy.acnhdemo.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dispy.acnhdemo.model.bean.Fossil
import com.google.gson.reflect.TypeToken
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FossilsViewModel : ViewModelBase() {

    private val fossils: MutableLiveData<List<Fossil>> by lazy {
        MutableLiveData<List<Fossil>>().also {
            loadData()
        }
    }

    fun getFossils(): LiveData<List<Fossil>> = fossils

    private fun loadData() {
        val call: Call<ResponseBody> = acnhService.getFossils()
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                val data = response.body()!!.string()
                val listFossils = ArrayList<Fossil>()
                val jsonFossils = JSONObject(data)
                for (eachKey in jsonFossils.keys()) {
                    val eachObject = jsonFossils.optJSONObject(eachKey)!!
                    listFossils.add(gson.fromJson(eachObject.toString(), object : TypeToken<Fossil>() {}.type))
                }
                fossils.value = listFossils
                Log.i("Fossils", "Get fossils success")
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.w("Fossils", "Error when get fossils", t)
            }

        })
    }

}