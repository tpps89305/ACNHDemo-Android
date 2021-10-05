package com.dispy.acnhdemo.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dispy.acnhdemo.databinding.ItemAvailableNowBinding
import com.dispy.acnhdemo.model.bean.AvailableNowInfo

/**
 * ACNH Demo
 * https://github.com/tpps89305/ACNHDemo-Android
 *
 * Created by Dispy on 2021/10/02
 * tpps89305@hotmail.com
 */
class AvailableNowAdapter(private val values: Array<AvailableNowInfo>) :
    RecyclerView.Adapter<AvailableNowAdapter.ViewHolder>() {

    private lateinit var listener: CatalogItemAdapter.OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemAvailableNowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            listener.onItemClick(position)
        }
    }

    override fun getItemCount(): Int = values.size

    fun swapItem(amount: Int, position: Int) {
        values[position].amount = amount.toString()
        notifyItemChanged(position)
    }

    inner class ViewHolder(private val binding: ItemAvailableNowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: AvailableNowInfo) {
            binding.availableNowInfo = item
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

}