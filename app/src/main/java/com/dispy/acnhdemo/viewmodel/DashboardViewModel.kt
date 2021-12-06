package com.dispy.acnhdemo.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.dispy.acnhdemo.model.ACNHRepository
import com.dispy.acnhdemo.model.DateHandler
import com.dispy.acnhdemo.model.bean.*
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.launch
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
class DashboardViewModel(private val repository: ACNHRepository) : ViewModelBase() {

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

    private val birthdayVillagers: MutableLiveData<ArrayList<Villager>> by lazy {
        MutableLiveData<ArrayList<Villager>>().also {
            loadBirthdayVillager()
        }
    }

    val allDailyTask: LiveData<List<DailyTask>> = repository.allDailyTask.asLiveData()

    fun getAvailableFishes(): LiveData<Int> = availableFishes

    fun getAvailableSeaCreature(): LiveData<Int> = availableSeaCreature

    fun getAvailableBugs(): LiveData<Int> = availableBugs

    fun getBirthdayVillager(): LiveData<ArrayList<Villager>> = birthdayVillagers

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
                    } else {
                        false
                    }
                } as ArrayList<Bug>
                availableBugs.value = listBug.size
                Log.i("Bugs", "Get available bugs success: ${listBug.size}")
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.w("Bugs", "Error when get bugs", t)
            }

        })
    }

    private fun loadFishes() {
        val currentMonth = DateHandler.getCurrentMonth()
        val currentHour = DateHandler.getCurrentHour()
        val call: Call<ResponseBody> = acnhService.getFishes()
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                val data = response.body()!!.string()
                var listFish = ArrayList<Fish>()
                val jsonFishes = JSONObject(data)
                for (eachKey in jsonFishes.keys()) {
                    val eachObject = jsonFishes.optJSONObject(eachKey)!!
                    listFish.add(gson.fromJson(eachObject.toString(), object : TypeToken<Fish>() {}.type))
                }
                listFish = listFish.filter {
                    if (it.availability.monthArrayNorthern.contains(currentMonth) || it.availability.isAllYear) {
                        it.availability.timeArray.contains(currentHour) || it.availability.isAllDay
                    } else {
                        false
                    }
                } as ArrayList<Fish>
                availableFishes.value = listFish.size
                Log.i("Fishes", "Get available fishes success: ${listFish.size}")
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.w("Fishes", "Error when get fishes", t)
            }

        })
    }

    private fun loadSeaCreatures() {
        val currentMonth = DateHandler.getCurrentMonth()
        val currentHour = DateHandler.getCurrentHour()
        val call: Call<ResponseBody> = acnhService.getSeaCreatures()
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                val data = response.body()!!.string()
                var listSeaCreature = ArrayList<SeaCreature>()
                val jsonSeaCreature = JSONObject(data)
                for (eachKey in jsonSeaCreature.keys()) {
                    val eachObject = jsonSeaCreature.optJSONObject(eachKey)!!
                    listSeaCreature.add(gson.fromJson(eachObject.toString(), object : TypeToken<SeaCreature>() {}.type))
                }
                listSeaCreature = listSeaCreature.filter {
                    if (it.availability.monthArrayNorthern.contains(currentMonth) || it.availability.isAllYear) {
                        it.availability.timeArray.contains(currentHour) || it.availability.isAllDay
                    } else {
                        false
                    }
                } as ArrayList<SeaCreature>
                availableSeaCreature.value = listSeaCreature.size
                Log.i("SeaCreature", "Get available sea creatures success: ${listSeaCreature.size}")
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.w("SeaCreature", "Error when get sea creatures", t)
            }

        })
    }

    private fun loadBirthdayVillager() {
        val today = DateHandler.getToday()
        val call: Call<ResponseBody> = acnhService.getVillagers()
        Log.d("Villagers", call.request().toString())
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                val data = response.body()!!.string()
                Log.d("Villagers", data)
                var listVillagers = ArrayList<Villager>()
                val jsonVillagers = JSONObject(data)
                for (eachKey in jsonVillagers.keys()) {
                    val eachObject = jsonVillagers.optJSONObject(eachKey)!!
                    listVillagers.add(gson.fromJson(eachObject.toString(), object : TypeToken<Villager>() {}.type))
                }
                listVillagers = listVillagers.filter {
                    it.birthday == today
                } as ArrayList<Villager>
                birthdayVillagers.value = listVillagers
                Log.i("Villagers", "Get birthday villagers success: ${listVillagers.size}")
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.w("Villagers", "Error when get villagers")
                Log.w("Villagers", t.message!!)
                birthdayVillagers.value = ArrayList()
            }

        })
    }

    fun updateDailyTask(dailyTask: DailyTask) = viewModelScope.launch {
        repository.update(dailyTask)
    }

    fun resetCurrentValue() = viewModelScope.launch {
        repository.resetCurrentValue()
    }

}

class DashboardViewModelFactory(private val repository: ACNHRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DashboardViewModel::class.java)) {
            return DashboardViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}