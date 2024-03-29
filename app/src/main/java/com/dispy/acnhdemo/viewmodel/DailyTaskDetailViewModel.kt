package com.dispy.acnhdemo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dispy.acnhdemo.repository.DatabaseRepository
import com.dispy.acnhdemo.model.bean.DailyTask

class DailyTaskDetailViewModel(private val repository: DatabaseRepository) : ViewModel() {
    val allDailyTask: LiveData<List<DailyTask>> = repository.allDailyTask.asLiveData()
}