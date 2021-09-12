package com.dispy.acnhdemo.viewmodel

import androidx.lifecycle.MutableLiveData
import com.dispy.acnhdemo.model.bean.Bug
import com.dispy.acnhdemo.model.bean.TitleContentPair

/**
 * ACNH Demo
 * https://github.com/tpps89305/ACNHDemo-Android
 *
 *
 * Created by Dispy on 2021/09/12
 * tpps89305@hotmail.com
 */
class BugDetailViewModel : ViewModelBase() {

    private val arrayBugInfo: MutableLiveData<List<TitleContentPair>> = MutableLiveData()

    fun parseData(bug: Bug) {
        val listDetailItems = ArrayList<TitleContentPair>()
        listDetailItems.add(TitleContentPair("Price", bug.price.toString()))
        listDetailItems.add(TitleContentPair("Price Flick", bug.priceFlick.toString()))
        listDetailItems.add(TitleContentPair("Location", bug.availability.location))
        listDetailItems.add(TitleContentPair("Rarity", bug.availability.rarity))

        this@BugDetailViewModel.arrayBugInfo.value = listDetailItems
    }

    fun getBugInfo(): MutableLiveData<List<TitleContentPair>> = arrayBugInfo

}