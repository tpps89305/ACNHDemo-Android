package com.dispy.acnhdemo.view.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.dispy.acnhdemo.R
import com.dispy.acnhdemo.databinding.FragmentFishesBinding
import com.dispy.acnhdemo.view.adapter.CommonAdapter
import com.dispy.acnhdemo.viewmodel.FishesViewModel

class FishesFragment : BaseFragment() {

    private var columnCount = 1
    private lateinit var viewModel: FishesViewModel
    private val fishesAdapter = CommonAdapter(ArrayList())
    private lateinit var binding: FragmentFishesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFishesBinding.inflate(layoutInflater)
        initActionBar("Fishes")
        viewModel = ViewModelProvider(this).get(FishesViewModel::class.java)

        with(binding.listFishes) {
            layoutManager = when {
                columnCount <= 1 -> LinearLayoutManager(context)
                else -> GridLayoutManager(context, columnCount)
            }
            adapter = fishesAdapter
        }

        viewModel.getFishes().observe(viewLifecycleOwner, { data ->
            fishesAdapter.swapItems(data)
        })

        fishesAdapter.setOnItemClickListener(object :
            CommonAdapter.OnItemClickListener {
            override fun onItemClick(view: View, fileName: String) {
                val selectedFish = viewModel.getFish(fileName)
                val action = FishesFragmentDirections.actionGotoFishDetailFragment(selectedFish)
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
                fishesAdapter.filter.filter(newText)
                return false
            }

        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                binding.root.findNavController().popBackStack()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}