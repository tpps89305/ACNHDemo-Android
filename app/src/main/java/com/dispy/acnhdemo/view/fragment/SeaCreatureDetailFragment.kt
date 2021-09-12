package com.dispy.acnhdemo.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.dispy.acnhdemo.databinding.FragmentSeaCreatureDetailBinding
import com.dispy.acnhdemo.model.DateHandler
import com.dispy.acnhdemo.view.adapter.AvailableMonthAdapter
import com.dispy.acnhdemo.view.adapter.TitleContentAdapter
import com.dispy.acnhdemo.view.layoutmanager.AvailableMonthLayoutManager
import com.dispy.acnhdemo.view.layoutmanager.DetailListLayoutManager
import com.dispy.acnhdemo.viewmodel.SeaCreatureDetailViewModel

class SeaCreatureDetailFragment : BaseFragment() {

    private val args: SeaCreatureDetailFragmentArgs by navArgs()
    private val viewModel: SeaCreatureDetailViewModel by viewModels()
    private val detailAdapter = TitleContentAdapter(ArrayList())
    private lateinit var availableMonthAdapter: AvailableMonthAdapter
    private lateinit var binding: FragmentSeaCreatureDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSeaCreatureDetailBinding.inflate(layoutInflater)
        initActionBar(args.seaCreature.name.nameTWzh)

        binding.seaCreature = args.seaCreature
        binding.lifecycleOwner = this

        with(binding.listSeaCreatureDetail) {
            layoutManager = DetailListLayoutManager(context)
            adapter = detailAdapter
        }

        with(viewModel) {
            parseData(args.seaCreature)
            getSeaCreatureInfo().observe(viewLifecycleOwner, { data ->
                detailAdapter.swapItems(data)
            })
        }

        availableMonthAdapter = AvailableMonthAdapter(
            args.seaCreature.availability.monthArrayNorthern,
            DateHandler.getCurrentMonth()
        )
        with(binding.listAvailableMonth) {
            layoutManager = AvailableMonthLayoutManager(context, 4)
            adapter = availableMonthAdapter
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