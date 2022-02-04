package com.dispy.acnhdemo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.dispy.acnhdemo.model.ACNHRepository
import com.dispy.acnhdemo.model.bean.DailyTask
import kotlinx.coroutines.launch

/**
 * Created by yangchaofu on 2022/2/4.
 *
 * @author yangchaofu
 * @since 2022/2/4
 */
class DailyTaskEditViewModel(private val repository: ACNHRepository) : ViewModelBase() {

    fun getDailyTask(uid: Int): LiveData<DailyTask> {
        return repository.getDailyTask(uid).asLiveData()
    }

    fun updateDailyTask(dailyTask: DailyTask) = viewModelScope.launch {
        repository.update(dailyTask)
    }

}