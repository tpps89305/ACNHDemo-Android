package com.dispy.acnhdemo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dispy.acnhdemo.model.ACNHRepository

class DashboardViewModelFactory(private val repository: ACNHRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DashboardViewModel::class.java)) {
            return DashboardViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(DailyTaskDetailViewModel::class.java)) {
            return DailyTaskDetailViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}