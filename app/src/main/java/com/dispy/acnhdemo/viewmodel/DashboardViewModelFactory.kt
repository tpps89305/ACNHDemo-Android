package com.dispy.acnhdemo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dispy.acnhdemo.model.ACNHRepository

class DashboardViewModelFactory(private val repository: ACNHRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(DashboardViewModel::class.java) -> {
                DashboardViewModel(repository) as T
            }
            modelClass.isAssignableFrom(DailyTaskDetailViewModel::class.java) -> {
                DailyTaskDetailViewModel(repository) as T
            }
            modelClass.isAssignableFrom(DailyTaskEditViewModel::class.java) -> {
                DailyTaskEditViewModel(repository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

}