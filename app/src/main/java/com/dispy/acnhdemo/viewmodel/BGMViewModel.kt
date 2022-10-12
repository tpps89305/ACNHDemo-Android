package com.dispy.acnhdemo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dispy.acnhdemo.model.bean.BGM
import com.dispy.acnhdemo.repository.NetworkRepository

/**
 * ACNH Demo
 * https://github.com/tpps89305/ACNHDemo-Android
 *
 * Created by Dispy on 2021/09/25
 * tpps89305@hotmail.com
 */
class BGMViewModel : ViewModel()  {

    private val bgmValue: MutableLiveData<List<BGM>> by lazy {
        MutableLiveData<List<BGM>>().also {
            loadData()
        }
    }

    fun getBGMValue(): LiveData<List<BGM>> = bgmValue

    private fun loadData() {
        val repository = NetworkRepository()
        repository.fetchBGMs(object : NetworkRepository.ResponseListener<List<BGM>>{
            override fun onResponse(response: List<BGM>) {
                bgmValue.value = response
            }

        })
    }

    fun getMusicURL(hour: String, weather: String): String {
        val bgms = bgmValue.value!!
        val result = bgms.filter {
            it.fileName.contains(String.format("%02d", hour.toInt())) && it.fileName.contains(weather)
        }
        if (result.isEmpty()) {
            return ""
        }
        return result[0].musicURI
    }

}