package com.dispy.acnhdemo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dispy.acnhdemo.model.bean.Song

class SongDetailViewModel : ViewModel() {

    private val song : MutableLiveData<Song> = MutableLiveData()

    fun getSong(): MutableLiveData<Song> {
        return song
    }

    fun getData(song: Song) {
        this@SongDetailViewModel.song.value = song
    }

    fun isSongOrderable(): Boolean {
        song.value?.let {
            return it.isOrderable
        }
        return false
    }

}