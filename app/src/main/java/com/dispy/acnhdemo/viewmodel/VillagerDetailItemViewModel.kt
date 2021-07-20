package com.dispy.acnhdemo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dispy.acnhdemo.model.bean.Villager
import com.dispy.acnhdemo.model.bean.VillagerDetailItem

class VillagerDetailItemViewModel {

    private val detailAvatarUrl: MutableLiveData<String> = MutableLiveData()
    private val listDetailItems: MutableLiveData<List<VillagerDetailItem>> = MutableLiveData()

    fun getData(villager: Villager) {
        val listDetailItems = ArrayList<VillagerDetailItem>()
        listDetailItems.add(VillagerDetailItem("Personality", villager.personality.name))
        listDetailItems.add(VillagerDetailItem("Birthday", villager.birthdayString))
        listDetailItems.add(VillagerDetailItem("Like", villager.bubbleColor))
        listDetailItems.add(VillagerDetailItem("Species", villager.species))
        listDetailItems.add(VillagerDetailItem("Gender", villager.gender.name))
        listDetailItems.add(VillagerDetailItem("Catch Phrase", villager.catchPhrase))

        this@VillagerDetailItemViewModel.listDetailItems.value = listDetailItems
        this@VillagerDetailItemViewModel.detailAvatarUrl.value = villager.imageURI
    }

    fun getItems(): LiveData<List<VillagerDetailItem>> {
        return listDetailItems
    }

}