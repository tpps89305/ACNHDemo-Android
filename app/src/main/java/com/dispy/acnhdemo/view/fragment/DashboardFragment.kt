package com.dispy.acnhdemo.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.dispy.acnhdemo.ACNHApplication
import com.dispy.acnhdemo.R
import com.dispy.acnhdemo.databinding.FragmentDashboardBinding
import com.dispy.acnhdemo.model.bean.AvailableNowInfo
import com.dispy.acnhdemo.model.bean.DailyTask
import com.dispy.acnhdemo.model.bean.Villager
import com.dispy.acnhdemo.view.adapter.AvailableNowAdapter
import com.dispy.acnhdemo.view.adapter.BirthdayVillagerAdapter
import com.dispy.acnhdemo.view.adapter.DailyTaskAdapter
import com.dispy.acnhdemo.view.layoutmanager.AvailableNowListLayoutManager
import com.dispy.acnhdemo.view.layoutmanager.DailyTaskListLayoutManager
import com.dispy.acnhdemo.viewmodel.DashboardViewModel
import com.dispy.acnhdemo.viewmodel.DashboardViewModelFactory

class DashboardFragment : BaseFragment() {

    private val viewModel: DashboardViewModel by viewModels {
        DashboardViewModelFactory((activity?.application as ACNHApplication).repository)
    }

    private val availableNowAdapter by lazy {
        val items = arrayOf(
            AvailableNowInfo(
                R.drawable.icon_fish,
                "0",
            ),
            AvailableNowInfo(
                R.drawable.icon_sea_creature,
                "0",
            ),
            AvailableNowInfo(
                R.drawable.icon_bug,
                "0",
            )
        )
        AvailableNowAdapter(items)
    }

    private val dailyTaskAdapter = DailyTaskAdapter()

    private val birthdayVillagerAdapter = BirthdayVillagerAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentDashboardBinding.inflate(layoutInflater)
        initActionBar(resources.getString(R.string.app_name), false)

        with(binding.listAvailableNow) {
            layoutManager = AvailableNowListLayoutManager(context)
            adapter = availableNowAdapter
        }

        with(binding.listDailyTask) {
            layoutManager = DailyTaskListLayoutManager(context)
            adapter = dailyTaskAdapter
        }

        with(binding.listBirthday) {
            layoutManager = AvailableNowListLayoutManager(context)
            adapter = birthdayVillagerAdapter
        }

        with(viewModel) {
            getAvailableBugs().observe(viewLifecycleOwner) { data ->
                availableNowAdapter.swapItem(data, 2)
            }
            getAvailableFishes().observe(viewLifecycleOwner) { data ->
                availableNowAdapter.swapItem(data, 0)
            }
            getAvailableSeaCreature().observe(viewLifecycleOwner) { data ->
                availableNowAdapter.swapItem(data, 1)
            }
            allDailyTask.observe(viewLifecycleOwner) { dailyTasks ->
                dailyTaskAdapter.submitList(dailyTasks)
                dailyTaskAdapter.setOnItemClickListener(object :
                    DailyTaskAdapter.OnItemClickListener {
                    override fun onItemClick(dailyTask: DailyTask) {
                        updateDailyTask(dailyTask)
                    }
                })
            }
            getBirthdayVillager().observe(viewLifecycleOwner) { data ->
                birthdayVillagerAdapter.submitList(data)
            }
        }

        binding.buttonReset.setOnClickListener {
            viewModel.resetCurrentValue()
        }

        binding.buttonDetail.setOnClickListener {
            val dailyTaskDetailFragment = DailyTaskDetailFragment()
            activity?.supportFragmentManager?.let { it1 ->
                dailyTaskDetailFragment.show(it1, "DailyTaskDetail")
            }
        }

        birthdayVillagerAdapter.setOnItemClickListener(object : BirthdayVillagerAdapter.OnItemClickListener {
            override fun onItemClick(villager: Villager) {
                val action = DashboardFragmentDirections.actionDashboardFragmentToBirthdayVillagerDetail(villager)
                binding.root.findNavController().navigate(action)
            }
        })

        availableNowAdapter.setOnItemClickListener(object : AvailableNowAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                val action = when (position) {
                    0 -> DashboardFragmentDirections.actionDashboardFragmentToNavFish(true)
                    1 -> DashboardFragmentDirections.actionDashboardFragmentToNavSeaCreature(true)
                    2 -> DashboardFragmentDirections.actionDashboardFragmentToNavBug(true)
                    else -> throw IllegalArgumentException("Cannot find the catalog $position")
                }
                binding.root.findNavController().navigate(action)
            }
        })

        return binding.root
    }

}