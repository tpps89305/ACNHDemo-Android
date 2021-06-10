package com.dispy.acnhdemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dispy.acnhdemo.databinding.FragmentVillagerDetailBinding

class VillagerDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentVillagerDetailBinding.inflate(layoutInflater)

        return binding.root
    }
}