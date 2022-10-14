package com.dispy.acnhdemo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dispy.acnhdemo.model.bean.Houseware
import com.dispy.acnhdemo.repository.NetworkRepository

class HousewaresViewModel : ViewModel() {

    private val housewares: MutableLiveData<List<Houseware>> by lazy {
        MutableLiveData<List<Houseware>>().also {
            loadData()
        }
    }

    fun getHousewares(): LiveData<List<Houseware>> = housewares

    fun getHouseware(fileName: String): Houseware {
        val result = housewares.value?.filter {
            it.fileName == fileName
        }
        return result!!.first()
    }

    private fun loadData() {
        val repository = NetworkRepository()
        repository.fetchHousewares(object : NetworkRepository.ResponseListener<List<Houseware>> {
            override fun onResponse(response: List<Houseware>) {
                housewares.value = response
            }

        })
    }

}