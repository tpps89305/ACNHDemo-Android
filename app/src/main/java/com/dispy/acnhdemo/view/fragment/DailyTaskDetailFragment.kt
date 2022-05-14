package com.dispy.acnhdemo.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dispy.acnhdemo.ACNHApplication
import com.dispy.acnhdemo.databinding.FragmentDailyTaskDetailBinding
import com.dispy.acnhdemo.model.bean.DailyTask
import com.dispy.acnhdemo.view.adapter.DailyTaskDetailAdapter
import com.dispy.acnhdemo.viewmodel.DailyTaskDetailViewModel
import com.dispy.acnhdemo.viewmodel.DashboardViewModelFactory
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class DailyTaskDetailFragment : BottomSheetDialogFragment() {

    private val viewModel: DailyTaskDetailViewModel by viewModels {
        DashboardViewModelFactory((activity?.application as ACNHApplication).repository)
    }
    private val dailyTaskDetailAdapter = DailyTaskDetailAdapter(ArrayList())
    private lateinit var binding: FragmentDailyTaskDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDailyTaskDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding.listDailyTaskDetail) {
            layoutManager = LinearLayoutManager(context)
            adapter = dailyTaskDetailAdapter
        }

        with(viewModel) {
            allDailyTask.observe(viewLifecycleOwner) { dailyTasks ->
                dailyTaskDetailAdapter.submitList(dailyTasks)
                dailyTaskDetailAdapter.setOnItemClickListener(object :
                DailyTaskDetailAdapter.OnItemClickListener {
                    override fun onItemClick(dailyTask: DailyTask) {
                        val dailyTaskEditFragment = DailyTaskEditFragment.newInstance(dailyTask.uid!!)
                        activity?.supportFragmentManager?.let { it1 ->
                            dailyTaskEditFragment.show(it1, "DailyTaskEditFragment")
                        }
                    }
                })
            }
        }

        binding.buttonClose.setOnClickListener {
            dismiss()
        }
    }

}