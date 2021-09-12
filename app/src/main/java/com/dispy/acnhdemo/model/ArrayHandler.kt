package com.dispy.acnhdemo.model

import com.dispy.acnhdemo.model.bean.Bug
import com.dispy.acnhdemo.model.bean.CommonItem
import com.dispy.acnhdemo.model.bean.Fish
import com.dispy.acnhdemo.model.bean.SeaCreature

/**
 * ACNH Demo
 * https://github.com/tpps89305/ACNHDemo-Android
 *
 * Created by Dispy on 2021/09/04
 * tpps89305@hotmail.com
 */
class ArrayHandler {

    companion object {
        fun parseFishesToCommonItems(fishes: List<Fish>): List<CommonItem> {
            val commonItems = ArrayList<CommonItem>()
            for (fish in fishes) {
                val tags = ArrayList<String>()
                tags.add("Sell: ${fish.price}")
                tags.add("Sell CJ: ${fish.priceCj}")
                val commonItem = CommonItem(fish.fileName, fish.name.nameTWzh, fish.iconURI, tags)
                commonItems.add(commonItem)
            }
            return commonItems
        }

        fun parseSeaCreaturesToCommonItems(seaCreatures: List<SeaCreature>): List<CommonItem> {
            val commonItems = ArrayList<CommonItem>()
            for (seaCreature in seaCreatures) {
                val tags = ArrayList<String>()
                tags.add("Sell: ${seaCreature.price}")
                val commonItem = CommonItem(seaCreature.fileName, seaCreature.name.nameTWzh, seaCreature.iconURI, tags)
                commonItems.add(commonItem)
            }
            return commonItems
        }

        fun parseBugsToCommonItems(bugs: List<Bug>): List<CommonItem> {
            val commonItems = ArrayList<CommonItem>()
            for (bug in bugs) {
                val tags = ArrayList<String>()
                tags.add("Sell: ${bug.price}")
                tags.add("Sell Flick: ${bug.priceFlick}")
                val commonItem = CommonItem(bug.fileName, bug.name.nameTWzh, bug.iconURI, tags)
                commonItems.add(commonItem)
            }
            return commonItems
        }

    }
}