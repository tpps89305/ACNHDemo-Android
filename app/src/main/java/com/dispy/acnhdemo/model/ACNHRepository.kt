package com.dispy.acnhdemo.model

import androidx.annotation.WorkerThread
import com.dispy.acnhdemo.model.bean.DailyTask
import kotlinx.coroutines.flow.Flow

/**
 * ACNH Demo
 * https://github.com/tpps89305/ACNHDemo-Android
 *
 * Created by Dispy on 2021/11/28
 * tpps89305@hotmail.com
 */
class ACNHRepository(private val dao: DailyTaskDao) {

    val allDailyTask: Flow<List<DailyTask>> = dao.getAll()

    @WorkerThread
    suspend fun insert(dailyTask: DailyTask) {
        dao.insertAll(dailyTask)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun update(dailyTask: DailyTask) {
        dao.update(dailyTask.currentValue!!, dailyTask.uid!!)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun resetCurrentValue() {
        dao.resetCurrentValue()
    }

}