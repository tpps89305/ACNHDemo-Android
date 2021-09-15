package com.dispy.acnhdemo.view.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dispy.acnhdemo.R
import com.dispy.acnhdemo.databinding.FragmentArtBinding
import com.dispy.acnhdemo.view.adapter.CommonAdapter
import com.dispy.acnhdemo.viewmodel.ArtViewModel

class ArtFragment : BaseFragment() {

    private lateinit var binding: FragmentArtBinding
    private lateinit var viewModel: ArtViewModel
    private val artAdapter = CommonAdapter(ArrayList())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentArtBinding.inflate(layoutInflater)
        initActionBar("Art")
        viewModel = ViewModelProvider(this).get(ArtViewModel::class.java)

        with(binding.listArt) {
            layoutManager = LinearLayoutManager(context)
            adapter = artAdapter
        }

        viewModel.getArt().observe(viewLifecycleOwner, { data ->
            artAdapter.swapItems(data)
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
                artAdapter.filter.filter(newText)
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