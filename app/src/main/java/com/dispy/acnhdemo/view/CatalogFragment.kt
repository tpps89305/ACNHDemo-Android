package com.dispy.acnhdemo.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dispy.acnhdemo.R
import com.dispy.acnhdemo.databinding.FragmentCatalogBinding

/**
 * A fragment representing a list of Items.
 */
class CatalogFragment : Fragment() {

    private var columnCount = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentCatalogBinding.inflate(layoutInflater)

        with(binding.listCatalog) {
            layoutManager = when {
                columnCount <= 1 -> LinearLayoutManager(context)
                else -> GridLayoutManager(context, columnCount)
            }
            adapter = CatalogRecyclerViewAdapter(resources.getStringArray(R.array.catalog_items))
        }

        return binding.root
    }
}