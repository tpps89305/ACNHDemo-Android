package com.dispy.acnhdemo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dispy.acnhdemo.model.DateHandler
import com.dispy.acnhdemo.model.bean.Bug
import com.dispy.acnhdemo.repository.NetworkRepository

class BugsViewModel : ViewModel() {

    private var isAvailableNow: Boolean = false

    private val bugs: MutableLiveData<List<Bug>> by lazy {
        MutableLiveData<List<Bug>>().also {
            loadData()
        }
    }

    fun getBugs(isAvailableNow: Boolean): LiveData<List<Bug>> {
        this.isAvailableNow = isAvailableNow
        return bugs
    }

    fun getBug(fileName: String): Bug {
        val result = bugs.value?.filter {
            it.fileName == fileName
        }
        return result!!.first()
    }

    private fun loadData() {
        val repository = NetworkRepository()
        repository.fetchBugs(object : NetworkRepository.ResponseListener<List<Bug>> {
            override fun onResponse(response: List<Bug>) {
                if (isAvailableNow) {
                    val currentMonth = DateHandler.getCurrentMonth()
                    val currentHour = DateHandler.getCurrentHour()
                    bugs.value = response.filter {
                        if (it.availability.monthArrayNorthern.contains(currentMonth) || it.availability.isAllYear) {
                            it.availability.timeArray.contains(currentHour) || it.availability.isAllDay
                        } else {
                            false
                        }
                    } as ArrayList<Bug>
                } else {
                    bugs.value = response
                }
            }
        })
    }

}