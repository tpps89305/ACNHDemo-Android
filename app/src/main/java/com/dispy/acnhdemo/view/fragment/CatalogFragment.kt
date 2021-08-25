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
import com.dispy.acnhdemo.view.adapter.CatalogItemAdapter

/**
 * A fragment representing a list of Items.
 */
class CatalogFragment : BaseFragment() {

    private var columnCount = 1
    private val catalogAdapter by lazy {
        CatalogItemAdapter(resources.getStringArray(R.array.catalog_items))
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
                    else -> throw IllegalArgumentException("Cannot find the catalog $position")
                }
                binding.root.findNavController().navigate(action)
            }

        })

        return binding.root
    }
}