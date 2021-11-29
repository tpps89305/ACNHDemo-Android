package com.dispy.acnhdemo

import android.app.Application
import com.dispy.acnhdemo.model.ACNHRepository
import com.dispy.acnhdemo.model.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

/**
 * ACNH Demo
 * https://github.com/tpps89305/ACNHDemo-Android
 *
 * Created by Dispy on 2021/11/28
 * tpps89305@hotmail.com
 */
class ACNHApplication: Application() {
    val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy { AppDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { ACNHRepository(database.dailyTaskDao()) }
}