package com.dispy.acnhdemo.model

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import com.dispy.acnhdemo.model.bean.DailyTask
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import com.dispy.acnhdemo.R

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
                    .addCallback(AppDatabaseCallback(context, scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

    private class AppDatabaseCallback(val context: Context, private val scope: CoroutineScope): RoomDatabase.Callback() {
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

            val dailyTask1 = DailyTask(name = "progress1", currentValue = 0, maxValue = 3, imageName = context.resources.getResourceEntryName(R.drawable.icon_ore_iron))
            val dailyTask2 = DailyTask(name = "progress2", currentValue = 0, maxValue = 3, imageName = context.resources.getResourceEntryName(R.drawable.icon_fossils))
            val dailyTask3 = DailyTask(name = "progress3", currentValue = 0, maxValue = 3, imageName = context.resources.getResourceEntryName(R.drawable.icon_pietro))
            val dailyTask4 = DailyTask(name = "progress4", currentValue = 0, maxValue = 3, imageName = context.resources.getResourceEntryName(R.drawable.icon_furniture))
            val dailyTask5 = DailyTask(name = "progress5", currentValue = 0, maxValue = 3, imageName = context.resources.getResourceEntryName(R.drawable.icon_1000_bells))
            val dailyTask6 = DailyTask(name = "progress6", currentValue = 0, maxValue = 3, imageName = context.resources.getResourceEntryName(R.drawable.icon_plane_ticket))
            val dailyTask7 = DailyTask(name = "progress7", currentValue = 0, maxValue = 3, imageName = context.resources.getResourceEntryName(R.drawable.icon_message_bottle))
            val dailyTask8 = DailyTask(name = "progress8", currentValue = 0, maxValue = 3, imageName = context.resources.getResourceEntryName(R.drawable.icon_recipe))

            dao.insertAll(dailyTask1, dailyTask2, dailyTask3, dailyTask4, dailyTask5, dailyTask6, dailyTask7, dailyTask8)
        }

    }


}