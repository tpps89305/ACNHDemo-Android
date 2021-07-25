package com.dispy.acnhdemo.viewmodel

import androidx.lifecycle.MutableLiveData
import com.dispy.acnhdemo.model.bean.Song

class SongDetailViewModel : ViewModelBase() {

    private val song : MutableLiveData<Song> = MutableLiveData()
    private val priceInfo : MutableLiveData<String> = MutableLiveData()

    fun getSong(): MutableLiveData<Song> {
        return song
    }

    fun getPriceInfo(): MutableLiveData<String> {
        val songValue = song.value!!
        var priceInfo = ""
        if (songValue.buyPrice != null) {
            priceInfo += "Buy: ${songValue.buyPrice}, "
        }
        priceInfo += "Sell: ${songValue.sellPrice}"
        this@SongDetailViewModel.priceInfo.value = priceInfo
        return this@SongDetailViewModel.priceInfo
    }

    fun getData(song: Song) {
        this@SongDetailViewModel.song.value = song
    }

}