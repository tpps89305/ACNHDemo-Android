package com.dispy.acnhdemo.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.dispy.acnhdemo.R
import com.dispy.acnhdemo.databinding.FragmentCatalogBinding

/**
 * A fragment representing a list of Items.
 */
class CatalogFragment : Fragment() {

    private var columnCount = 1
    private val catalogAdapter by lazy {
        CatalogItemAdapter(resources.getStringArray(R.array.catalog_items))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentCatalogBinding.inflate(layoutInflater)
        (activity as AppCompatActivity).supportActionBar?.title = "Catalog"

        with(binding.listCatalog) {
            layoutManager = when {
                columnCount <= 1 -> LinearLayoutManager(context)
                else -> GridLayoutManager(context, columnCount)
            }
            adapter = catalogAdapter
        }

        catalogAdapter.setOnItemClickListener(object : CatalogItemAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                val action = CatalogFragmentDirections.actionCatalogFragmentToSongsFragment()
                binding.root.findNavController().navigate(action)
            }

        })

        return binding.root
    }
}