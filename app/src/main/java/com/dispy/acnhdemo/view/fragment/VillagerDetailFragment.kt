package com.dispy.acnhdemo.view.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.dispy.acnhdemo.databinding.FragmentVillagerDetailBinding
import com.dispy.acnhdemo.view.adapter.VillagerDetailItemAdapter
import com.dispy.acnhdemo.view.layoutmanager.DetailListLayoutManager
import com.dispy.acnhdemo.viewmodel.VillagerDetailItemViewModel

class VillagerDetailFragment : BaseFragment() {

    private val args: VillagerDetailFragmentArgs by navArgs()
    private val detailAdapter = VillagerDetailItemAdapter(ArrayList())
    private val viewModel = VillagerDetailItemViewModel()
    private lateinit var binding: FragmentVillagerDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        sharedElementEnterTransition = MaterialContainerTransform()
        binding = FragmentVillagerDetailBinding.inflate(layoutInflater)
        initActionBar(args.villager.name.nameTWzh)
        //TODO: Fix IndexOutOfBoundsException from ArrayList.get <- FragmentTransitionImpl.setNameOverridesReordered
//        ViewCompat.setTransitionName(binding.root, "ant00")

        with(binding.recyclerViewDetail) {
            layoutManager = DetailListLayoutManager(context)
            adapter = detailAdapter
            setBackgroundColor(Color.parseColor(args.villager.bubbleColor))
        }

        with(viewModel) {
            getData(args.villager)
            getItems().observe(viewLifecycleOwner, { data ->
                detailAdapter.swapItems(data)
            })
            detailAdapter.swapImageUrl(args.villager.imageURI)
            detailAdapter.swapTextColor(Color.parseColor(args.villager.textColor))
        }

        return binding.root
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