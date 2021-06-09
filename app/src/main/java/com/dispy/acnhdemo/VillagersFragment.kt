package com.dispy.acnhdemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dispy.acnhdemo.databinding.FragmentVillagersListBinding
import com.dispy.acnhdemo.function.VillagerViewModel

/**
 * A fragment representing a list of Items.
 */
class VillagersFragment : Fragment() {

    private var columnCount = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = FragmentVillagersListBinding.inflate(layoutInflater)
        val view = binding.root

        val villagerAdapter = VillagersRecyclerViewAdapter(ArrayList())
        with(binding.listVillagers) {
            layoutManager = when {
                columnCount <= 1 -> LinearLayoutManager(context)
                else -> GridLayoutManager(context, columnCount)
            }
            adapter = villagerAdapter
        }

        val viewModel = VillagerViewModel()
        with(viewModel) {
            getVillagers().observe(viewLifecycleOwner, { data ->
                villagerAdapter.swapItems(data)
            })
            getData()
        }

        return view
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
                VillagersFragment().apply {
                    arguments = Bundle().apply {
                        putInt(ARG_COLUMN_COUNT, columnCount)
                    }
                }
    }
}