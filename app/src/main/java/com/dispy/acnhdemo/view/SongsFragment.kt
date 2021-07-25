package com.dispy.acnhdemo.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.dispy.acnhdemo.databinding.FragmentSongsBinding
import com.dispy.acnhdemo.model.bean.Song
import com.dispy.acnhdemo.viewmodel.SongsFragmentViewModel

/**
 * A fragment representing a list of Items.
 */
class SongsFragment : Fragment() {

    private var columnCount = 1
    private val songsAdapter = SongsAdapter(ArrayList())
    private val viewModel = SongsFragmentViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSongsBinding.inflate(layoutInflater)

        (activity as AppCompatActivity).supportActionBar?.title = "Songs"

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

}