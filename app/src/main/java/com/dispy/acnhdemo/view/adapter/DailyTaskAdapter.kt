package com.dispy.acnhdemo.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dispy.acnhdemo.databinding.ItemDailyTaskBinding
import com.dispy.acnhdemo.model.bean.DailyTask

/**
 * ACNH Demo
 * https://github.com/tpps89305/ACNHDemo-Android
 *
 * Created by Dispy on 2021/11/28
 * tpps89305@hotmail.com
 */
class DailyTaskAdapter: RecyclerView.Adapter<DailyTaskAdapter.ViewHolder>() {

    private var listener: OnItemClickListener? = null
    private val list: ArrayList<DailyTask> = arrayListOf()

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

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
        holder.bind(list[position])
        holder.itemView.setOnClickListener {
            list[position].currentValue = list[position].currentValue?.plus(1)
            listener?.onItemClick(list[position])
        }
    }

    override fun getItemCount(): Int = list.count()

    fun submitList(newList: List<DailyTask>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemDailyTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(dailyTask: DailyTask) {
                binding.dailyTask = dailyTask
                binding.executePendingBindings()
            }
    }

    interface OnItemClickListener {
        fun onItemClick(dailyTask: DailyTask)
    }

}