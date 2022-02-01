package com.dispy.acnhdemo.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dispy.acnhdemo.databinding.ItemDailyTaskDetailBinding
import com.dispy.acnhdemo.model.bean.DailyTask

/**
 * Created by yangchaofu on 2022/2/1.
 *
 * @author yangchaofu
 * @since 2022/2/1
 */
class DailyTaskDetailAdapter(private val dailyTaskItems: ArrayList<DailyTask>) :
    RecyclerView.Adapter<DailyTaskDetailAdapter.ViewHolder>() {

    private var listener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemDailyTaskDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dailyTaskItems[position])
        holder.itemView.setOnClickListener {

        }
    }

    override fun getItemCount(): Int {
        return dailyTaskItems.size
    }

    fun submitList(newList: List<DailyTask>) {
        dailyTaskItems.clear()
        dailyTaskItems.addAll(newList)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemDailyTaskDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DailyTask) {
            binding.dailyTask = item
            binding.executePendingBindings()
        }
    }

    interface OnItemClickListener {
        fun onItemClick(dailyTask: DailyTask)
    }

}