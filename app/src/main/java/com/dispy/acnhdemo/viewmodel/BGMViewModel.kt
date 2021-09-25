package com.dispy.acnhdemo.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dispy.acnhdemo.model.bean.BGM
import com.google.gson.reflect.TypeToken
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * ACNH Demo
 * https://github.com/tpps89305/ACNHDemo-Android
 *
 * Created by Dispy on 2021/09/25
 * tpps89305@hotmail.com
 */
class BGMViewModel : ViewModelBase()  {

    private val bgmValue: MutableLiveData<List<BGM>> by lazy {
        MutableLiveData<List<BGM>>().also {
            loadData()
        }
    }

    fun getBGMValue(): LiveData<List<BGM>> = bgmValue

    private fun loadData() {
        val call: Call<ResponseBody> = acnhService.getBGM()
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                val data = response.body()!!.string()
                val listBGM = ArrayList<BGM>()
                val jsonBGM = JSONObject(data)
                for (eachKey in jsonBGM.keys()) {
                    val eachObject = jsonBGM.optJSONObject(eachKey)!!
                    listBGM.add(gson.fromJson(eachObject.toString(), object : TypeToken<BGM>() {}.type))
                }
                bgmValue.value = listBGM
                Log.i("BGMs", "Get BGMs success")
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.w("BGMs", "Error when get BGMs", t)
            }

        })
    }

    fun getMusicURL(hour: String, weather: String): String {
        val bgms = bgmValue.value!!
        val result = bgms.filter {
            it.fileName.contains(String.format("%02d", hour.toInt())) && it.fileName.contains(weather)
        }
        if (result.isEmpty()) {
            return ""
        }
        return result[0].musicURI
    }

}