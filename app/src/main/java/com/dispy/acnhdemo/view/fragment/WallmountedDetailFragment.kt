package com.dispy.acnhdemo.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.dispy.acnhdemo.databinding.FragmentWallmountedDetailBinding
import com.dispy.acnhdemo.view.adapter.CommonDetailAdapter
import com.dispy.acnhdemo.view.layoutmanager.DetailListLayoutManager
import com.dispy.acnhdemo.viewmodel.WallmountedDetailViewModel

class WallmountedDetailFragment : BaseFragment() {

    private val args: WallmountedDetailFragmentArgs by navArgs()
    private lateinit var binding: FragmentWallmountedDetailBinding
    private lateinit var viewModel: WallmountedDetailViewModel
    private lateinit var commonDetailAdapter: CommonDetailAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWallmountedDetailBinding.inflate(layoutInflater)
        initActionBar(args.wallmounted.name.nameTWzh)
        viewModel = ViewModelProvider(this).get(WallmountedDetailViewModel::class.java)
        commonDetailAdapter = CommonDetailAdapter(
            args.wallmounted.imageURI,
            viewModel.getDetailInfo(args.wallmounted)
        )

        with(binding.listWallmounted) {
            layoutManager = DetailListLayoutManager(context)
            adapter = commonDetailAdapter
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