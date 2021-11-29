package com.dispy.acnhdemo.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.dispy.acnhdemo.model.bean.DailyTask
import kotlinx.coroutines.flow.Flow

/**
 * ACNH Demo
 * https://github.com/tpps89305/ACNHDemo-Android
 *
 * Created by Dispy on 2021/11/28
 * tpps89305@hotmail.com
 */
@Dao
interface DailyTaskDao {

    @Query("SELECT * FROM dailyTask")
    fun getAll(): Flow<List<DailyTask>>

    @Insert
    suspend fun insertAll(vararg dailyTask: DailyTask)

    @Query("DELETE FROM dailyTask")
    fun deleteAll()

    @Query("UPDATE dailyTask SET current_value = :currentValue WHERE uid = :uid")
    suspend fun update(currentValue: Int, uid: Int)

    @Query("UPDATE dailyTask SET current_value = 0")
    suspend fun resetCurrentValue()

}