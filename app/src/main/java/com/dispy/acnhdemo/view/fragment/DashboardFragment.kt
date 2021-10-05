package com.dispy.acnhdemo.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.dispy.acnhdemo.R
import com.dispy.acnhdemo.databinding.FragmentDashboardBinding
import com.dispy.acnhdemo.model.bean.AvailableNowInfo
import com.dispy.acnhdemo.view.adapter.AvailableNowAdapter
import com.dispy.acnhdemo.view.layoutmanager.AvailableNowListLayoutManager
import com.dispy.acnhdemo.viewmodel.DashboardViewModel

class DashboardFragment : BaseFragment() {

    private lateinit var viewModel: DashboardViewModel

    private val availableNowAdapter by lazy {
        val items = arrayOf(
            AvailableNowInfo(
                R.drawable.icon_bug,
                "0",
                resources.getString(R.string.bugs)
            ),
            AvailableNowInfo(
                R.drawable.icon_fish,
                "0", resources.getString(R.string.fishes)
            ),
            AvailableNowInfo(
                R.drawable.icon_sea_creature,
                "0",
                resources.getString(R.string.sea_creatures)
            )
        )
        AvailableNowAdapter(items)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentDashboardBinding.inflate(layoutInflater)
        initActionBar(resources.getString(R.string.app_name), false)
        viewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)

        with(binding.listAvailableNow) {
            layoutManager = AvailableNowListLayoutManager(context)
            adapter = availableNowAdapter
        }

        with(viewModel) {
            getAvailableBugs().observe(viewLifecycleOwner, { data ->
                availableNowAdapter.swapItem(data, 0)
            })
            getAvailableFishes().observe(viewLifecycleOwner, { data ->
                availableNowAdapter.swapItem(data, 1)
            })
            getAvailableSeaCreature().observe(viewLifecycleOwner, { data ->
                availableNowAdapter.swapItem(data, 2)
            })
        }

        return binding.root
    }

}