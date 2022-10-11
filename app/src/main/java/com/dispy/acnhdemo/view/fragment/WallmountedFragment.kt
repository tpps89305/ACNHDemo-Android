package com.dispy.acnhdemo.view.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dispy.acnhdemo.R
import com.dispy.acnhdemo.databinding.FragmentWallmountedBinding
import com.dispy.acnhdemo.view.adapter.CommonAdapter
import com.dispy.acnhdemo.viewmodel.WallmountedViewModel

class WallmountedFragment : BaseFragment() {

    private lateinit var binding: FragmentWallmountedBinding
    private lateinit var viewModel: WallmountedViewModel
    private val wallmountedAdapter = CommonAdapter(ArrayList())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWallmountedBinding.inflate(layoutInflater)
        initActionBar("Wallmounted")
        viewModel = ViewModelProvider(this)[WallmountedViewModel::class.java]

        with(binding.listWallmounted) {
            layoutManager = LinearLayoutManager(context)
            adapter = wallmountedAdapter
        }

        viewModel.getWallmounteds().observe(viewLifecycleOwner) { data ->
            wallmountedAdapter.swapItems(data)
        }

        wallmountedAdapter.setOnItemClickListener(object : CommonAdapter.OnItemClickListener {
            override fun onItemClick(view: View, fileName: String) {
                val selected = viewModel.getWallmounted(fileName)
                val action = WallmountedFragmentDirections.actionWallmountedFragmentToWallmountedDetailFragment(selected)
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
                wallmountedAdapter.filter.filter(newText)
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