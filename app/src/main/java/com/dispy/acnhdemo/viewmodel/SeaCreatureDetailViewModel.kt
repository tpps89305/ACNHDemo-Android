package com.dispy.acnhdemo.viewmodel

import androidx.lifecycle.MutableLiveData
import com.dispy.acnhdemo.model.bean.SeaCreature
import com.dispy.acnhdemo.model.bean.TitleContentPair

/**
 * ACNH Demo
 * https://github.com/tpps89305/ACNHDemo-Android
 *
 * Created by Dispy on 2021/09/12
 * tpps89305@hotmail.com
 */
class SeaCreatureDetailViewModel : ViewModelBase() {

    private val arraySeaCreatureInfo: MutableLiveData<List<TitleContentPair>> = MutableLiveData()

    fun parseData(seaCreature: SeaCreature) {
        val listDetailItems = ArrayList<TitleContentPair>()
        listDetailItems.add(TitleContentPair("Price", seaCreature.price.toString()))
        listDetailItems.add(TitleContentPair("Speed", seaCreature.speed))

        this@SeaCreatureDetailViewModel.arraySeaCreatureInfo.value = listDetailItems
    }

    fun getSeaCreatureInfo(): MutableLiveData<List<TitleContentPair>> = arraySeaCreatureInfo

}