package com.dispy.acnhdemo.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dispy.acnhdemo.model.bean.Bug
import com.google.gson.reflect.TypeToken
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BugsViewModel : ViewModelBase() {

    private val bugs: MutableLiveData<List<Bug>> by lazy {
        MutableLiveData<List<Bug>>().also {
            loadData()
        }
    }

    fun getBugs(): LiveData<List<Bug>> = bugs

    fun getBug(fileName: String): Bug {
        val result = bugs.value?.filter {
            it.fileName == fileName
        }
        return result!!.first()
    }

    private fun loadData() {
        val call: Call<ResponseBody> = acnhService.getBugs()
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                val data = response.body()!!.string()
                val listBug = ArrayList<Bug>()
                val jsonBugs = JSONObject(data)
                for (eachKey in jsonBugs.keys()) {
                    val eachObject = jsonBugs.optJSONObject(eachKey)!!
                    listBug.add(gson.fromJson(eachObject.toString(), object : TypeToken<Bug>() {}.type))
                }
                bugs.value = listBug
                Log.i("Bugs", "Get bugs success")
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.w("Bugs", "Error when get bugs", t)
            }

        })
    }

}