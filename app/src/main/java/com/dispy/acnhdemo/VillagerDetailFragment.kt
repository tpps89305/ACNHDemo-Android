package com.dispy.acnhdemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.dispy.acnhdemo.databinding.FragmentVillagerDetailBinding

class VillagerDetailFragment : Fragment() {

    private val args: VillagerDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        sharedElementEnterTransition = MaterialContainerTransform()
        val binding = FragmentVillagerDetailBinding.inflate(layoutInflater)
        //TODO: Fix IndexOutOfBoundsException from ArrayList.get <- FragmentTransitionImpl.setNameOverridesReordered
//        ViewCompat.setTransitionName(binding.root, "ant00")

        binding.villager = args.villager
        return binding.root
    }
}