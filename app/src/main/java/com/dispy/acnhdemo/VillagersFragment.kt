package com.dispy.acnhdemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.dispy.acnhdemo.databinding.FragmentVillagersListBinding
import com.dispy.acnhdemo.function.VillagerViewModel

/**
 * A fragment representing a list of Items.
 */
class VillagersFragment : Fragment() {

    private var columnCount = 1
    private val villagerAdapter = VillagersRecyclerViewAdapter(ArrayList())
    private val viewModel = VillagerViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentVillagersListBinding.inflate(layoutInflater)

        with(binding.listVillagers) {
            layoutManager = when {
                columnCount <= 1 -> LinearLayoutManager(context)
                else -> GridLayoutManager(context, columnCount)
            }
            adapter = villagerAdapter
        }

        with(viewModel) {
            getVillagers().observe(viewLifecycleOwner, { data ->
                villagerAdapter.swapItems(data)
            })
        }

        villagerAdapter.setOnItemClickListener(object : VillagersRecyclerViewAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                val action = VillagersFragmentDirections.actionShowVillagerDetail()
                binding.root.findNavController().navigate(action)
            }

        })

        return binding.root
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