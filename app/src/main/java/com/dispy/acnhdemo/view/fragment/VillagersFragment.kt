package com.dispy.acnhdemo.view.fragment

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.dispy.acnhdemo.R
import com.dispy.acnhdemo.model.bean.Villager
import com.dispy.acnhdemo.databinding.FragmentVillagersListBinding
import com.dispy.acnhdemo.view.adapter.VillagersAdapter
import com.dispy.acnhdemo.viewmodel.VillagerViewModel

/**
 * A fragment representing a list of Items.
 */
class VillagersFragment : BaseFragment() {

    private var columnCount = 1
    private val villagerAdapter = VillagersAdapter(ArrayList())
    private val viewModel: VillagerViewModel by viewModels()
    private lateinit var binding: FragmentVillagersListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVillagersListBinding.inflate(layoutInflater)
        initActionBar("Villagers", false)
        //TODO: Fix IndexOutOfBoundsException from ArrayList.get <- FragmentTransitionImpl.setNameOverridesReordered
//        exitTransition = Hold()
//        exitTransition = MaterialElevationScale(/* growing= */ false)
//        reenterTransition = MaterialElevationScale(/* growing= */ true)

        setHasOptionsMenu(true)
        with(binding.listVillagers) {
            layoutManager = when {
                columnCount <= 1 -> LinearLayoutManager(context)
                else -> GridLayoutManager(context, columnCount)
            }
            adapter = villagerAdapter
        }

        binding.progressLoading.visibility = View.VISIBLE
        with(viewModel) {
            getVillagers().observe(viewLifecycleOwner) { data ->
                binding.progressLoading.visibility = View.GONE
                if (data.isNotEmpty()) {
                    villagerAdapter.swapItems(data)
                } else {
                    binding.groupNoResult.visibility = View.VISIBLE
                }
            }
        }

        villagerAdapter.setOnItemClickListener(object :
            VillagersAdapter.OnItemClickListener {
            override fun onItemClick(view: View, position: Int, villager: Villager) {
//                val extras = FragmentNavigatorExtras(view to villager.fileName)
                val action =
                    VillagersFragmentDirections.actionShowVillagerDetail(
                        villager
                    )
//                binding.root.findNavController().navigate(action, extras)
                binding.root.findNavController().navigate(action)
            }

        })

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        activity?.menuInflater?.inflate(R.menu.search, menu)
        val menuItem = menu.findItem(R.id.action_search)
        val searchView = menuItem.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                villagerAdapter.filter.filter(newText)
                return false
            }

        })
    }

}