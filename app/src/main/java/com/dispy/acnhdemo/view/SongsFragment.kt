package com.dispy.acnhdemo.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dispy.acnhdemo.R
import com.dispy.acnhdemo.databinding.FragmentSongsBinding
import com.dispy.acnhdemo.dummy.DummyContent
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

        return binding.root
    }

}