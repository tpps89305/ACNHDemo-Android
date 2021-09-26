package com.dispy.acnhdemo.viewmodel

import com.dispy.acnhdemo.model.bean.TitleContentPair
import com.dispy.acnhdemo.model.bean.Wallmounted

class WallmountedDetailViewModel : ViewModelBase() {

    fun getDetailInfo(wallmounted: Wallmounted): ArrayList<TitleContentPair> {
        val listDetailItems = ArrayList<TitleContentPair>()
        if (wallmounted.buyPrice != null) {
            listDetailItems.add(TitleContentPair("Buy Price", wallmounted.buyPrice.toString()))
        }
        listDetailItems.add(TitleContentPair("Sell Price", wallmounted.sellPrice.toString()))
        listDetailItems.add(TitleContentPair("Tag", wallmounted.tag))
        listDetailItems.add(TitleContentPair("Size", wallmounted.size))
        return listDetailItems
    }

}