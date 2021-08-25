package com.dispy.acnhdemo.view

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.dispy.acnhdemo.R
import com.dispy.acnhdemo.databinding.FragmentSongsBinding
import com.dispy.acnhdemo.model.bean.Song
import com.dispy.acnhdemo.viewmodel.SongsFragmentViewModel

/**
 * A fragment representing a list of Items.
 */
class SongsFragment : BaseFragment() {

    private var columnCount = 1
    private val songsAdapter = SongsAdapter(ArrayList())
    private val viewModel = SongsFragmentViewModel()
    private lateinit var binding: FragmentSongsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSongsBinding.inflate(layoutInflater)
        initActionBar("Songs")
        with(binding.listSongs) {
            layoutManager = when {
                columnCount <= 1 -> LinearLayoutManager(context)
                else -> GridLayoutManager(context, columnCount)
            }
            adapter = songsAdapter
        }

        with(viewModel) {
            getSongs().observe(viewLifecycleOwner, { data ->
                songsAdapter.swapItems(data)
            })
        }

        songsAdapter.setOnItemClickListener(object : SongsAdapter.OnItemClickListener {
            override fun onItemClick(view: View, position: Int, song: Song) {
                val action = SongsFragmentDirections.actionSongsFragmentToSongDetailFragment(song)
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
                songsAdapter.filter.filter(newText)
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