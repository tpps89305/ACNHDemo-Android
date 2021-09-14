package com.dispy.acnhdemo.view.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dispy.acnhdemo.R
import com.dispy.acnhdemo.viewmodel.FossilsViewModel
import com.dispy.acnhdemo.databinding.FragmentFossilsBinding
import com.dispy.acnhdemo.view.adapter.CommonAdapter

class FossilsFragment : BaseFragment() {

    private lateinit var binding: FragmentFossilsBinding
    private lateinit var viewModel: FossilsViewModel
    private val fossilsAdapter = CommonAdapter(ArrayList())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFossilsBinding.inflate(layoutInflater)
        initActionBar("Fossils")
        viewModel = ViewModelProvider(this).get(FossilsViewModel::class.java)

        with(binding.listFossils) {
            layoutManager = LinearLayoutManager(context)
            adapter = fossilsAdapter
        }

        viewModel.getFossils().observe(viewLifecycleOwner, { data ->
            fossilsAdapter.swapItems(data)
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
                fossilsAdapter.filter.filter(newText)
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