package com.dispy.acnhdemo.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dispy.acnhdemo.databinding.ItemDailyTaskBinding

/**
 * ACNH Demo
 * https://github.com/tpps89305/ACNHDemo-Android
 *
 * Created by Dispy on 2021/11/28
 * tpps89305@hotmail.com
 */
class DailyTaskAdapter: RecyclerView.Adapter<DailyTaskAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyTaskAdapter.ViewHolder {
        return ViewHolder(
            ItemDailyTaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DailyTaskAdapter.ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int = 8

    inner class ViewHolder(private val binding: ItemDailyTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind() {
                TODO("bind data to ItemDailyTask")
            }
    }

}