package com.dispy.acnhdemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.dispy.acnhdemo.databinding.FragmentVillagerDetailBinding
import com.google.android.material.transition.MaterialContainerTransform

class VillagerDetailFragment : Fragment() {

    private val args: VillagerDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sharedElementEnterTransition = MaterialContainerTransform()
        val binding = FragmentVillagerDetailBinding.inflate(layoutInflater)
        ViewCompat.setTransitionName(binding.root, args.fileName)
        return binding.root
    }
}