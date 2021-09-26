package com.dispy.acnhdemo.view.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dispy.acnhdemo.R
import com.dispy.acnhdemo.databinding.FragmentHousewaresBinding
import com.dispy.acnhdemo.view.adapter.CommonAdapter
import com.dispy.acnhdemo.viewmodel.HousewaresViewModel

class HousewaresFragment : BaseFragment() {

    private lateinit var binding: FragmentHousewaresBinding
    private lateinit var viewModel: HousewaresViewModel
    private val housewaresAdapter = CommonAdapter(ArrayList())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHousewaresBinding.inflate(layoutInflater)
        initActionBar("Housewares")
        viewModel = ViewModelProvider(this).get(HousewaresViewModel::class.java)

        with(binding.listHousewares) {
            layoutManager = LinearLayoutManager(context)
            adapter = housewaresAdapter
        }

        viewModel.getHousewares().observe(viewLifecycleOwner, { data ->
            housewaresAdapter.swapItems(data)
        })

        housewaresAdapter.setOnItemClickListener(object: CommonAdapter.OnItemClickListener {
            override fun onItemClick(view: View, fileName: String) {
                val selected = viewModel.getHouseware(fileName)
                val action = HousewaresFragmentDirections.actionHousewaresFragmentToHousewareDetailFragment(selected)
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
                housewaresAdapter.filter.filter(newText)
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