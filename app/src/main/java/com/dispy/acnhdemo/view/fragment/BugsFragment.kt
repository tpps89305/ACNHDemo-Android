package com.dispy.acnhdemo.view.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dispy.acnhdemo.R
import com.dispy.acnhdemo.databinding.FragmentBugsBinding
import com.dispy.acnhdemo.view.adapter.CommonAdapter
import com.dispy.acnhdemo.viewmodel.BugsViewModel

class BugsFragment : BaseFragment() {

    private lateinit var binding: FragmentBugsBinding
    private lateinit var viewModel: BugsViewModel
    private val bugsAdapter = CommonAdapter(ArrayList())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBugsBinding.inflate(layoutInflater)
        initActionBar("Bugs")
        viewModel = ViewModelProvider(this).get(BugsViewModel::class.java)

        with(binding.listBugs) {
            layoutManager = LinearLayoutManager(context)
            adapter = bugsAdapter
        }

        viewModel.getBugs().observe(viewLifecycleOwner) { data ->
            bugsAdapter.swapItems(data)
        }

        bugsAdapter.setOnItemClickListener(object :
            CommonAdapter.OnItemClickListener {
            override fun onItemClick(view: View, fileName: String) {
                val selected = viewModel.getBug(fileName)
                val action = BugsFragmentDirections.actionBugsFragmentToBugDetailFragment(selected)
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
                bugsAdapter.filter.filter(newText)
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