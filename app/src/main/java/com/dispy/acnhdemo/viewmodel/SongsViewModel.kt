package com.dispy.acnhdemo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dispy.acnhdemo.model.bean.Song
import com.dispy.acnhdemo.repository.NetworkRepository

class SongsViewModel: ViewModel() {

    private val songs: MutableLiveData<List<Song>> by lazy {
        MutableLiveData<List<Song>>().also {
            loadData()
        }
    }

    fun getSongs(): LiveData<List<Song>> = songs

    private fun loadData() {
        val repository = NetworkRepository()
        repository.fetchSongs(object : NetworkRepository.ResponseListener<List<Song>> {
            override fun onResponse(response: List<Song>) {
                songs.value = response
            }
        })
    }

}
