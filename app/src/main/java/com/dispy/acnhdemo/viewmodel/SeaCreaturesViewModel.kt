package com.dispy.acnhdemo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dispy.acnhdemo.model.DateHandler
import com.dispy.acnhdemo.model.bean.SeaCreature
import com.dispy.acnhdemo.repository.NetworkRepository

class SeaCreaturesViewModel : ViewModel() {

    private var isAvailableNow: Boolean = false

    private val seaCreatures: MutableLiveData<List<SeaCreature>> by lazy {
        MutableLiveData<List<SeaCreature>>().also {
            loadData()
        }
    }

    fun getSeaCreatures(isAvailableNow: Boolean): LiveData<List<SeaCreature>> {
        this.isAvailableNow = isAvailableNow
        return seaCreatures
    }

    fun getSeaCreature(fileName: String): SeaCreature {
        val result = seaCreatures.value?.filter {
            it.fileName == fileName
        }
        return result!!.first()
    }

    private fun loadData() {
        val repository = NetworkRepository()
        repository.fetchSeaCreatures(object : NetworkRepository.ResponseListener<List<SeaCreature>> {
            override fun onResponse(response: List<SeaCreature>) {
                if (isAvailableNow) {
                    val currentMonth = DateHandler.getCurrentMonth()
                    val currentHour = DateHandler.getCurrentHour()
                    seaCreatures.value = response.filter {
                        if (it.availability.monthArrayNorthern.contains(currentMonth) || it.availability.isAllYear) {
                            it.availability.timeArray.contains(currentHour) || it.availability.isAllDay
                        } else {
                            false
                        }
                    } as ArrayList<SeaCreature>
                } else {
                    seaCreatures.value = response
                }
            }
        })
    }
}