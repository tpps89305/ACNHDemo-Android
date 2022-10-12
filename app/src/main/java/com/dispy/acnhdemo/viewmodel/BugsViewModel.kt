package com.dispy.acnhdemo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dispy.acnhdemo.model.bean.Bug
import com.dispy.acnhdemo.repository.NetworkRepository

class BugsViewModel : ViewModel() {

    private val bugs: MutableLiveData<List<Bug>> by lazy {
        MutableLiveData<List<Bug>>().also {
            loadData()
        }
    }

    fun getBugs(): LiveData<List<Bug>> = bugs

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
                bugs.value = response
            }
        })
    }

}