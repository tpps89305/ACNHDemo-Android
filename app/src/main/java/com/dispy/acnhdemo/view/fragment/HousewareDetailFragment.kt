package com.dispy.acnhdemo.view.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.dispy.acnhdemo.databinding.FragmentHousewareDetailBinding
import com.dispy.acnhdemo.view.adapter.CommonDetailAdapter
import com.dispy.acnhdemo.view.layoutmanager.DetailListLayoutManager
import com.dispy.acnhdemo.viewmodel.HousewareDetailViewModel

class HousewareDetailFragment : BaseFragment() {

    private val args: HousewareDetailFragmentArgs by navArgs()
    private lateinit var commonAdapter: CommonDetailAdapter
    private lateinit var binding: FragmentHousewareDetailBinding
    private lateinit var viewModel: HousewareDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHousewareDetailBinding.inflate(layoutInflater)
        initActionBar(args.houseware.name.nameTWzh)
        viewModel = ViewModelProvider(this).get(HousewareDetailViewModel::class.java)
        commonAdapter = CommonDetailAdapter(args.houseware.imageURI, viewModel.getDetailInfo(args.houseware))

        with(binding.listHousewareDetail) {
            layoutManager = DetailListLayoutManager(context)
            adapter = commonAdapter
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