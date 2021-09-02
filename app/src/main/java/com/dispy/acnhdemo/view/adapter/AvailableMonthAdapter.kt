package com.dispy.acnhdemo.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dispy.acnhdemo.databinding.ItemAvailableMonthBinding

class AvailableMonthAdapter(private val arrayAvailableMonth: List<Int>, private val currentMonth: Int) : RecyclerView.Adapter<AvailableMonthAdapter.ViewHolder>() {

    private val arrayMonth =
        arrayOf("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemAvailableMonthBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(arrayMonth[position], isAvailableMonth = arrayAvailableMonth.contains(position + 1), isCurrentMonth = position + 1 == currentMonth)
    }

    override fun getItemCount(): Int = arrayMonth.size

    inner class ViewHolder(private val binding: ItemAvailableMonthBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(month: String, isAvailableMonth: Boolean, isCurrentMonth: Boolean) {
            binding.textMonth.text = month
            binding.textMonth.isSelected = isAvailableMonth
            binding.viewCurrentMonthHighlight.visibility = if (isCurrentMonth) {
                View.VISIBLE
            } else {
                View.INVISIBLE
            }
        }
    }

}