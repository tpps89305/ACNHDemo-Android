package com.dispy.acnhdemo.model

import com.dispy.acnhdemo.model.bean.*

/**
 * ACNH Demo
 * https://github.com/tpps89305/ACNHDemo-Android
 *
 * Created by Dispy on 2021/09/04
 * tpps89305@hotmail.com
 */
class ArrayHandler {

    companion object {

        @JvmName("parseFishesToCommonItems")
        fun parseToCommonItems(fishes: List<Fish>): List<CommonItem> {
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

        @JvmName("parseSeaCreaturesToCommonItems")
        fun parseToCommonItems(seaCreatures: List<SeaCreature>): List<CommonItem> {
            val commonItems = ArrayList<CommonItem>()
            for (seaCreature in seaCreatures) {
                val tags = ArrayList<String>()
                tags.add("Sell: ${seaCreature.price}")
                val commonItem = CommonItem(seaCreature.fileName, seaCreature.name.nameTWzh, seaCreature.iconURI, tags)
                commonItems.add(commonItem)
            }
            return commonItems
        }

        @JvmName("parseBugsToCommonItems")
        fun parseToCommonItems(bugs: List<Bug>): List<CommonItem> {
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

        @JvmName("parseFossilsToCommonItems")
        fun parseToCommonItems(fossils: List<Fossil>): List<CommonItem> {
            val commonItems = ArrayList<CommonItem>()
            for (fossil in fossils) {
                val tags = ArrayList<String>()
                tags.add("Sell: ${fossil.price}")
                val commonItem =
                    CommonItem(fossil.fileName, fossil.name.nameTWzh, fossil.imageURI, tags)
                commonItems.add(commonItem)
            }
            return commonItems
        }

        @JvmName("parseArtToCommonItems")
        fun parseToCommonItems(art: List<Art>): List<CommonItem> {
            val commonItems = ArrayList<CommonItem>()
            for (each in art) {
                val tags = ArrayList<String>()
                tags.add("Buy: ${each.buyPrice}")
                tags.add("Sell: ${each.sellPrice}")
                val commonItem =
                    CommonItem(each.fileName, each.name.nameTWzh, each.imageURI, tags)
                commonItems.add(commonItem)
            }
            return commonItems
        }

    }
}