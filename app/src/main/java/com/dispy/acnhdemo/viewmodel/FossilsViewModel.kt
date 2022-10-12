package com.dispy.acnhdemo.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dispy.acnhdemo.model.bean.Fossil
import com.dispy.acnhdemo.repository.NetworkRepository
import com.google.gson.reflect.TypeToken
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FossilsViewModel : ViewModel() {

    private val fossils: MutableLiveData<List<Fossil>> by lazy {
        MutableLiveData<List<Fossil>>().also {
            loadData()
        }
    }

    fun getFossils(): LiveData<List<Fossil>> = fossils

    private fun loadData() {
        val repository = NetworkRepository()
        repository.fetchFossils(object : NetworkRepository.ResponseListener<List<Fossil>> {
            override fun onResponse(response: List<Fossil>) {
                fossils.value = response
            }

        })
    }

}