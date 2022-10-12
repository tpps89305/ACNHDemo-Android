package com.dispy.acnhdemo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dispy.acnhdemo.model.bean.Art
import com.dispy.acnhdemo.repository.NetworkRepository

class ArtViewModel : ViewModel() {

    private val art: MutableLiveData<List<Art>> by lazy {
        MutableLiveData<List<Art>>().also {
            loadData()
        }
    }

    fun getArt(): LiveData<List<Art>> = art

    private fun loadData() {
        val repository = NetworkRepository()
        repository.fetchArt(object : NetworkRepository.ResponseListener<List<Art>> {
            override fun onResponse(response: List<Art>) {
                art.value = response
            }

        })
    }
}