package com.dispy.acnhdemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.dispy.acnhdemo.databinding.FragmentVillagerDetailBinding
import com.dispy.acnhdemo.function.VillagerDetailItemViewModel

class VillagerDetailFragment : Fragment() {

    private val args: VillagerDetailFragmentArgs by navArgs()
    private val detailAdapter = VillagerDetailItemRecyclerViewAdapter(ArrayList(), "")
    private val viewModel = VillagerDetailItemViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        sharedElementEnterTransition = MaterialContainerTransform()
        val binding = FragmentVillagerDetailBinding.inflate(layoutInflater)
        //TODO: Fix IndexOutOfBoundsException from ArrayList.get <- FragmentTransitionImpl.setNameOverridesReordered
//        ViewCompat.setTransitionName(binding.root, "ant00")

        with(binding.recyclerViewDetail) {
            layoutManager = LinearLayoutManager(context)
            adapter = detailAdapter
        }

        with(viewModel) {
            getData(args.villager)
            getItems().observe(viewLifecycleOwner, { data ->
                detailAdapter.swapItems(data)
            })
            getAvatarUrl().observe(viewLifecycleOwner, { data ->
                detailAdapter.swapItems(data)
            })
        }

        return binding.root
    }
}