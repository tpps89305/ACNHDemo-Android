package com.dispy.acnhdemo.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dispy.acnhdemo.model.DateHandler
import com.dispy.acnhdemo.model.bean.Bug
import com.dispy.acnhdemo.model.bean.Fish
import com.dispy.acnhdemo.model.bean.SeaCreature
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
 * Created by Dispy on 2021/10/02
 * tpps89305@hotmail.com
 */
class DashboardViewModel : ViewModelBase() {

    private val availableFishes: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>().also {
            loadFishes()
        }
    }

    private val availableSeaCreature: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>().also {
            loadSeaCreatures()
        }
    }

    private val availableBugs: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>().also {
            loadBugs()
        }
    }

    fun getAvailableFishes(): LiveData<Int> = availableFishes

    fun getAvailableSeaCreature(): LiveData<Int> = availableSeaCreature

    fun getAvailableBugs(): LiveData<Int> = availableBugs

    private fun loadBugs() {
        val currentMonth = DateHandler.getCurrentMonth()
        val currentHour = DateHandler.getCurrentHour()
        val call: Call<ResponseBody> = acnhService.getBugs()
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                val data = response.body()!!.string()
                var listBug = ArrayList<Bug>()
                val jsonBugs = JSONObject(data)
                for (eachKey in jsonBugs.keys()) {
                    val eachObject = jsonBugs.optJSONObject(eachKey)!!
                    listBug.add(gson.fromJson(eachObject.toString(), object : TypeToken<Bug>() {}.type))
                }
                listBug = listBug.filter {
                    if (it.availability.monthArrayNorthern.contains(currentMonth) || it.availability.isAllYear) {
                        it.availability.timeArray.contains(currentHour) || it.availability.isAllDay
                    }
                    false
                } as ArrayList<Bug>
                availableBugs.value = listBug.size
                Log.i("Bugs", "Get bugs success")
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.w("Bugs", "Error when get bugs", t)
            }

        })
    }

    private fun loadFishes() {
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
                availableFishes.value = listFish.size
                Log.i("Fishes", "Get fishes success")
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.w("Fishes", "Error when get fishes", t)
            }

        })
    }

    private fun loadSeaCreatures() {
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
                availableSeaCreature.value = listSeaCreature.size
                Log.i("SeaCreature", "Get sea creatures success")
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.w("SeaCreature", "Error when get sea creatures", t)
            }

        })
    }

}