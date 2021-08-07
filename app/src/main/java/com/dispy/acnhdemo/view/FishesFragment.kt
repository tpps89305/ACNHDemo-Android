package com.dispy.acnhdemo.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.dispy.acnhdemo.databinding.FragmentFishesBinding
import com.dispy.acnhdemo.viewmodel.FishesViewModel

class FishesFragment : Fragment() {

    private var columnCount = 1
    private lateinit var viewModel: FishesViewModel
    private val fishesAdapter = FishesAdapter(ArrayList())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentFishesBinding.inflate(layoutInflater)
        (activity as AppCompatActivity).supportActionBar?.title = "Fishes"
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
        return binding.root
    }

}