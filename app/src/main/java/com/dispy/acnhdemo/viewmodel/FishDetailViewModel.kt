package com.dispy.acnhdemo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dispy.acnhdemo.model.bean.Fish
import com.dispy.acnhdemo.model.bean.TitleContentPair

class FishDetailViewModel : ViewModel() {

    private val arrayFishInfo: MutableLiveData<List<TitleContentPair>> = MutableLiveData()

    fun parseData(fish: Fish) {
        val listDetailItems = ArrayList<TitleContentPair>()
        listDetailItems.add(TitleContentPair("Price", fish.price.toString()))
        listDetailItems.add(TitleContentPair("Price CJ", fish.priceCj.toString()))
        listDetailItems.add(TitleContentPair("Location", fish.availability.location))
        listDetailItems.add(TitleContentPair("Rarity", fish.availability.rarity))

        this@FishDetailViewModel.arrayFishInfo.value = listDetailItems
    }

    fun getFishInfo(): MutableLiveData<List<TitleContentPair>> {
        return arrayFishInfo
    }

}