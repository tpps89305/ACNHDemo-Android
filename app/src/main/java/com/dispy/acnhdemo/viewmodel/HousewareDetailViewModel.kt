package com.dispy.acnhdemo.viewmodel

import com.dispy.acnhdemo.model.bean.Houseware
import com.dispy.acnhdemo.model.bean.TitleContentPair

class HousewareDetailViewModel : ViewModelBase() {

    fun getDetailInfo(houseware: Houseware): ArrayList<TitleContentPair> {
        val listDetailItems = ArrayList<TitleContentPair>()
        if (houseware.buyPrice != null) {
            listDetailItems.add(TitleContentPair("Buy Price", houseware.buyPrice.toString()))
        }
        listDetailItems.add(TitleContentPair("Sell Price", houseware.sellPrice.toString()))
        listDetailItems.add(TitleContentPair("Tag", houseware.tag))
        listDetailItems.add(TitleContentPair("Size", houseware.size))
        return listDetailItems
    }

}