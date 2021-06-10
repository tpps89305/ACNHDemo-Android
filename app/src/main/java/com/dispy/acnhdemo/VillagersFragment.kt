package com.dispy.acnhdemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.dispy.acnhdemo.databinding.FragmentVillagersListBinding
import com.dispy.acnhdemo.function.VillagerViewModel
import com.google.android.material.transition.Hold
import com.google.android.material.transition.MaterialElevationScale

/**
 * A fragment representing a list of Items.
 */
class VillagersFragment : Fragment() {

    private var columnCount = 1
    private val villagerAdapter = VillagersRecyclerViewAdapter(ArrayList())
    private val viewModel = VillagerViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentVillagersListBinding.inflate(layoutInflater)
        exitTransition = Hold()
        exitTransition = MaterialElevationScale(/* growing= */ false)
        reenterTransition = MaterialElevationScale(/* growing= */ true)

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

        villagerAdapter.setOnItemClickListener(object :
            VillagersRecyclerViewAdapter.OnItemClickListener {
            override fun onItemClick(view: View, position: Int, fileName: String) {
                val extras = FragmentNavigatorExtras(view to fileName)
                val action = VillagersFragmentDirections.actionShowVillagerDetail(fileName)
                binding.root.findNavController().navigate(action, extras)
            }

        })

        return binding.root
    }

}