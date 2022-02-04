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

    @Query("SELECT * FROM dailyTask WHERE uid IS :uid")
    fun get(uid: Int): Flow<DailyTask>

    @Insert
    suspend fun insertAll(vararg dailyTask: DailyTask)

    @Query("DELETE FROM dailyTask")
    fun deleteAll()

    @Query("UPDATE dailyTask SET current_value = :currentValue, max_value = :maxValue, name = :name WHERE uid = :uid")
    suspend fun update(uid: Int, name: String, currentValue: Int, maxValue: Int)

    @Query("UPDATE dailyTask SET current_value = 0")
    suspend fun resetCurrentValue()

}