package com.dispy.acnhdemo.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dispy.acnhdemo.model.bean.SeaCreature
import com.dispy.acnhdemo.model.bean.SeaCreaturesMap
import com.dispy.acnhdemo.repository.NetworkRepository
import com.google.gson.reflect.TypeToken
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SeaCreaturesViewModel : ViewModelBase() {

    private val seaCreatures: MutableLiveData<List<SeaCreature>> by lazy {
        MutableLiveData<List<SeaCreature>>().also {
            loadData()
        }
    }

    fun getSeaCreatures(): LiveData<List<SeaCreature>> {
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
                seaCreatures.value = response
            }
        })
    }
}