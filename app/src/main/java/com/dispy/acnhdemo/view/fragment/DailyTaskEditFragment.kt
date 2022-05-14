package com.dispy.acnhdemo.view.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.dispy.acnhdemo.ACNHApplication
import com.dispy.acnhdemo.R
import com.dispy.acnhdemo.databinding.FragmentDailyTaskEditBinding
import com.dispy.acnhdemo.model.bean.DailyTask
import com.dispy.acnhdemo.viewmodel.DailyTaskEditViewModel
import com.dispy.acnhdemo.viewmodel.DashboardViewModelFactory
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_DAILY_TASK_ID = "dailyTaskId"

/**
 * A simple [Fragment] subclass.
 * Use the [DailyTaskEditFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DailyTaskEditFragment : BottomSheetDialogFragment() {
    private val viewModel: DailyTaskEditViewModel by viewModels {
        DashboardViewModelFactory((activity?.application as ACNHApplication).repository)
    }
    private var dailyTaskUid: Int? = null
    private lateinit var binding: FragmentDailyTaskEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            dailyTaskUid = it.getInt(ARG_DAILY_TASK_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDailyTaskEditBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.getDailyTask(dailyTaskUid!!).observe(viewLifecycleOwner) {
            binding.textTaskName.text = it.name
            binding.editTaskName.setText(it.name)
            binding.editCurrentProgress.setText(it.currentValue.toString())
            binding.editMaxProgress.setText(it.maxValue.toString())
        }

        binding.buttonClose.setOnClickListener {
            dismiss()
        }

        binding.buttonSave.setOnClickListener {

            // TODO: Check input

            if (binding.editTaskName.text.toString().isEmpty()) {
                val dialog = AlertDialog.Builder(activity!!)
                dialog.setTitle(R.string.hint)
                dialog.setMessage(R.string.must_have_task_name)
                dialog.setPositiveButton(R.string.ok, null)
                dialog.show()
                return@setOnClickListener
            }

            if (binding.editCurrentProgress.text.toString().isEmpty()) {
                val dialog = AlertDialog.Builder(activity!!)
                dialog.setTitle(R.string.hint)
                dialog.setMessage(R.string.must_have_current_value)
                dialog.setPositiveButton(R.string.ok, null)
                dialog.show()
                return@setOnClickListener
            }
            if (binding.editMaxProgress.text.toString().isEmpty()) {
                val dialog = AlertDialog.Builder(activity!!)
                dialog.setTitle(R.string.hint)
                dialog.setMessage(R.string.must_have_max_value)
                dialog.setPositiveButton(R.string.ok, null)
                dialog.show()
                return@setOnClickListener
            }
            val newDailyTask = DailyTask(
                uid = dailyTaskUid,
                name = binding.editTaskName.text.toString(),
                currentValue = binding.editCurrentProgress.text.toString().toInt(),
                maxValue = binding.editMaxProgress.text.toString().toInt()
            )
            viewModel.updateDailyTask(newDailyTask)
            dismiss()
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param dailyTaskUid Parameter 1.
         * @return A new instance of fragment DailyTaskEditFragment.
         */
        @JvmStatic
        fun newInstance(dailyTaskUid: Int) =
            DailyTaskEditFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_DAILY_TASK_ID, dailyTaskUid)
                }
            }
    }
}