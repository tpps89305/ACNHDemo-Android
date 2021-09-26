package com.dispy.acnhdemo.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.dispy.acnhdemo.R
import com.dispy.acnhdemo.databinding.FragmentCatalogBinding
import com.dispy.acnhdemo.model.bean.CatalogItem
import com.dispy.acnhdemo.view.adapter.CatalogItemAdapter

/**
 * A fragment representing a list of Items.
 */
class CatalogFragment : BaseFragment() {

    private var columnCount = 1
    private val catalogAdapter by lazy {
        val catalogItems = arrayOf(
            CatalogItem(R.drawable.icon_music, resources.getString(R.string.songs)),
            CatalogItem(R.drawable.icon_fish, resources.getString(R.string.fishes)),
            CatalogItem(R.drawable.icon_sea_creature, resources.getString(R.string.sea_creatures)),
            CatalogItem(R.drawable.icon_bug, resources.getString(R.string.bugs)),
            CatalogItem(R.drawable.icon_fossils, resources.getString(R.string.fossils)),
            CatalogItem(R.drawable.icon_art, resources.getString(R.string.art)),
            CatalogItem(R.drawable.icon_music, resources.getString(R.string.bgm)),
            CatalogItem(R.drawable.icon_houseware, resources.getString(R.string.housewares)),
            CatalogItem(R.drawable.icon_wallmounted, resources.getString(R.string.wallmounted))
        )
        CatalogItemAdapter(catalogItems)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentCatalogBinding.inflate(layoutInflater)
        initActionBar("Catalog", false)

        with(binding.listCatalog) {
            layoutManager = when {
                columnCount <= 1 -> LinearLayoutManager(context)
                else -> GridLayoutManager(context, columnCount)
            }
            adapter = catalogAdapter
        }

        catalogAdapter.setOnItemClickListener(object : CatalogItemAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                val action = when (position) {
                    0 -> CatalogFragmentDirections.actionCatalogFragmentToSongsFragment()
                    1 -> CatalogFragmentDirections.actionCatalogFragmentToFishesFragment()
                    2 -> CatalogFragmentDirections.actionCatalogFragmentToSeaCreaturesFragment()
                    3 -> CatalogFragmentDirections.actionCatalogFragmentToBugsFragment()
                    4 -> CatalogFragmentDirections.actionCatalogFragmentToFossilsFragment()
                    5 -> CatalogFragmentDirections.actionCatalogFragmentToArtFragment()
                    6 -> CatalogFragmentDirections.actionCatalogFragmentToBGMFragment()
                    7 -> CatalogFragmentDirections.actionCatalogFragmentToHousewaresFragment()
                    8 -> CatalogFragmentDirections.actionCatalogFragmentToWallmountedFragment()
                    else -> throw IllegalArgumentException("Cannot find the catalog $position")
                }
                binding.root.findNavController().navigate(action)
            }

        })

        return binding.root
    }
}