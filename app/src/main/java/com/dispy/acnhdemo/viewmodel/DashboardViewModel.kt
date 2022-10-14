package com.dispy.acnhdemo.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.dispy.acnhdemo.repository.DatabaseRepository
import com.dispy.acnhdemo.model.DateHandler
import com.dispy.acnhdemo.model.bean.*
import com.dispy.acnhdemo.repository.NetworkRepository
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
class DashboardViewModel(private val repository: DatabaseRepository) : ViewModel() {

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

    private val networkRepository = NetworkRepository()

    private fun loadBugs() {
        val currentMonth = DateHandler.getCurrentMonth()
        val currentHour = DateHandler.getCurrentHour()
        networkRepository.fetchBugs(object : NetworkRepository.ResponseListener<List<Bug>> {
            override fun onResponse(response: List<Bug>) {
                val listBug = response.filter {
                    if (it.availability.monthArrayNorthern.contains(currentMonth) || it.availability.isAllYear) {
                        it.availability.timeArray.contains(currentHour) || it.availability.isAllDay
                    } else {
                        false
                    }
                } as ArrayList<Bug>
                availableBugs.value = listBug.size
            }
        })
    }

    private fun loadFishes() {
        val currentMonth = DateHandler.getCurrentMonth()
        val currentHour = DateHandler.getCurrentHour()
        networkRepository.fetchFishes(object : NetworkRepository.ResponseListener<List<Fish>>{
            override fun onResponse(response: List<Fish>) {
                val listFish = response.filter {
                    if (it.availability.monthArrayNorthern.contains(currentMonth) || it.availability.isAllYear) {
                        it.availability.timeArray.contains(currentHour) || it.availability.isAllDay
                    } else {
                        false
                    }
                } as ArrayList<Fish>
                availableFishes.value = listFish.size
            }
        })
    }

    private fun loadSeaCreatures() {
        val currentMonth = DateHandler.getCurrentMonth()
        val currentHour = DateHandler.getCurrentHour()
        networkRepository.fetchSeaCreatures(object : NetworkRepository.ResponseListener<List<SeaCreature>> {
            override fun onResponse(response: List<SeaCreature>) {
                val listSeaCreature = response.filter {
                    if (it.availability.monthArrayNorthern.contains(currentMonth) || it.availability.isAllYear) {
                        it.availability.timeArray.contains(currentHour) || it.availability.isAllDay
                    } else {
                        false
                    }
                } as ArrayList<SeaCreature>
                availableSeaCreature.value = listSeaCreature.size
            }
        })
    }

    private fun loadBirthdayVillager() {
        val today = DateHandler.getToday()
        networkRepository.fetchVillagers(object : NetworkRepository.ResponseListener<List<Villager>> {
            override fun onResponse(response: List<Villager>) {
                val listVillagers = response.filter {
                    it.birthday == today
                } as ArrayList<Villager>
                birthdayVillagers.value = listVillagers
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
