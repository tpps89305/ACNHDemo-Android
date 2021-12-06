package com.dispy.acnhdemo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dispy.acnhdemo.model.bean.Villager
import com.dispy.acnhdemo.model.bean.TitleContentPair

class VillagerDetailItemViewModel {

    private val detailAvatarUrl: MutableLiveData<String> = MutableLiveData()
    private val listDetailItems: MutableLiveData<List<TitleContentPair>> = MutableLiveData()

    fun getData(villager: Villager) {
        val listDetailItems = ArrayList<TitleContentPair>()
        listDetailItems.add(TitleContentPair("Personality", villager.personality.name))
        listDetailItems.add(TitleContentPair("Birthday", villager.birthday))
        listDetailItems.add(TitleContentPair("Like", villager.bubbleColor))
        listDetailItems.add(TitleContentPair("Species", villager.species))
        listDetailItems.add(TitleContentPair("Gender", villager.gender.name))
        listDetailItems.add(TitleContentPair("Catch Phrase", villager.catchPhrase))

        this@VillagerDetailItemViewModel.listDetailItems.value = listDetailItems
        this@VillagerDetailItemViewModel.detailAvatarUrl.value = villager.imageURI
    }

    fun getItems(): LiveData<List<TitleContentPair>> {
        return listDetailItems
    }

}