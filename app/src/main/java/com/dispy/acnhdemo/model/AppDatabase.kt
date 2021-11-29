package com.dispy.acnhdemo.model

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import com.dispy.acnhdemo.model.bean.DailyTask
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * ACNH Demo
 * https://github.com/tpps89305/ACNHDemo-Android
 *
 * Created by Dispy on 2021/11/28
 * tpps89305@hotmail.com
 */
@Database(entities = [DailyTask::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun dailyTaskDao(): DailyTaskDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "anch_database"
                )
                    .addCallback(AppDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

    private class AppDatabaseCallback(private val scope: CoroutineScope): RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.dailyTaskDao())
                }
            }
        }

        suspend fun populateDatabase(dao: DailyTaskDao) {
            dao.deleteAll()

            val dailyTask1 = DailyTask(name = "progress1", currentValue = 0, maxValue = 3)
            val dailyTask2 = DailyTask(name = "progress2", currentValue = 0, maxValue = 3)
            val dailyTask3 = DailyTask(name = "progress3", currentValue = 0, maxValue = 3)
            val dailyTask4 = DailyTask(name = "progress4", currentValue = 0, maxValue = 3)
            val dailyTask5 = DailyTask(name = "progress5", currentValue = 0, maxValue = 3)
            val dailyTask6 = DailyTask(name = "progress6", currentValue = 0, maxValue = 3)
            val dailyTask7 = DailyTask(name = "progress7", currentValue = 0, maxValue = 3)
            val dailyTask8 = DailyTask(name = "progress8", currentValue = 0, maxValue = 3)

            dao.insertAll(dailyTask1, dailyTask2, dailyTask3, dailyTask4, dailyTask5, dailyTask6, dailyTask7, dailyTask8)
        }

    }


}