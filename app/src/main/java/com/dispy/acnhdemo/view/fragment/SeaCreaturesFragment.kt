package com.dispy.acnhdemo.view.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dispy.acnhdemo.R
import com.dispy.acnhdemo.databinding.FragmentSeaCreaturesBinding
import com.dispy.acnhdemo.view.adapter.CommonAdapter
import com.dispy.acnhdemo.viewmodel.SeaCreaturesViewModel

class SeaCreaturesFragment : BaseFragment() {

    private lateinit var viewModel: SeaCreaturesViewModel
    private lateinit var binding: FragmentSeaCreaturesBinding
    private val seaCreaturesAdapter = CommonAdapter(ArrayList())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSeaCreaturesBinding.inflate(layoutInflater)
        initActionBar("Sea Creatures")
        viewModel = ViewModelProvider(this).get(SeaCreaturesViewModel::class.java)

        with(binding.listSeaCreatures) {
            layoutManager = LinearLayoutManager(context)
            adapter = seaCreaturesAdapter
        }

        viewModel.getSeaCreatures().observe(viewLifecycleOwner, { data ->
            seaCreaturesAdapter.swapItems(data)
        })

        seaCreaturesAdapter.setOnItemClickListener(object :
        CommonAdapter.OnItemClickListener {
            override fun onItemClick(view: View, fileName: String) {
                val selected = viewModel.getSeaCreature(fileName)
                val action = SeaCreaturesFragmentDirections.actionSeaCreaturesFragmentToSeaCreatureDetailFragment(selected)
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
                seaCreaturesAdapter.filter.filter(newText)
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