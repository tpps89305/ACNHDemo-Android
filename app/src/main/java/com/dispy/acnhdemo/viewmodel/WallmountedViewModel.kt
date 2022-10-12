package com.dispy.acnhdemo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dispy.acnhdemo.model.bean.Wallmounted
import com.dispy.acnhdemo.repository.NetworkRepository

class WallmountedViewModel : ViewModel() {

    private val wallmounteds: MutableLiveData<List<Wallmounted>> by lazy {
        MutableLiveData<List<Wallmounted>>().also {
            loadData()
        }
    }

    fun getWallmounteds(): LiveData<List<Wallmounted>> = wallmounteds

    fun getWallmounted(fileName: String): Wallmounted {
        val result = wallmounteds.value?.filter {
            it.fileName == fileName
        }
        return result!!.first()
    }

    private fun loadData() {
        val repository = NetworkRepository()
        repository.fetchWallmounteds(object :
            NetworkRepository.ResponseListener<List<Wallmounted>> {
            override fun onResponse(response: List<Wallmounted>) {
                wallmounteds.value = response
            }

        })
    }

}