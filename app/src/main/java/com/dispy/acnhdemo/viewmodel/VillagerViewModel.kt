package com.dispy.acnhdemo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dispy.acnhdemo.model.bean.Villager
import com.dispy.acnhdemo.repository.NetworkRepository

class VillagerViewModel: ViewModel() {

    private val villagers: MutableLiveData<List<Villager>> by lazy {
        MutableLiveData<List<Villager>>().also {
            loadVillagers()
        }
    }

    fun getVillagers(): LiveData<List<Villager>> {
        return villagers
    }

    private fun loadVillagers() {
        NetworkRepository().fetchVillagers(object : NetworkRepository.ResponseListener<List<Villager>> {
            override fun onResponse(response: List<Villager>) {
                villagers.value = response
            }
        })
    }

}