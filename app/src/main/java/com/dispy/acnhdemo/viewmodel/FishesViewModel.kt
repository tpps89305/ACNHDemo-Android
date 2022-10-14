package com.dispy.acnhdemo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dispy.acnhdemo.model.DateHandler
import com.dispy.acnhdemo.model.bean.Fish
import com.dispy.acnhdemo.repository.NetworkRepository

class FishesViewModel : ViewModel() {

    private var isAvailableNow: Boolean = false

    private val fishes: MutableLiveData<List<Fish>> by lazy {
        MutableLiveData<List<Fish>>().also {
            loadData()
        }
    }

    fun getFishes(isAvailableNow: Boolean): LiveData<List<Fish>> {
        this.isAvailableNow = isAvailableNow
        return fishes
    }

    fun getFish(fileName: String): Fish {
        val result = fishes.value?.filter {
            it.fileName == fileName
        }
        return result!!.first()
    }

    private fun loadData() {
        val repository = NetworkRepository()
        repository.fetchFishes(object : NetworkRepository.ResponseListener<List<Fish>>{
            override fun onResponse(response: List<Fish>) {
                if (isAvailableNow) {
                    val currentMonth = DateHandler.getCurrentMonth()
                    val currentHour = DateHandler.getCurrentHour()
                    fishes.value = response.filter {
                        if (it.availability.monthArrayNorthern.contains(currentMonth) || it.availability.isAllYear) {
                            it.availability.timeArray.contains(currentHour) || it.availability.isAllDay
                        } else {
                            false
                        }
                    } as ArrayList<Fish>
                } else {
                    fishes.value = response
                }
            }
        })
    }
}