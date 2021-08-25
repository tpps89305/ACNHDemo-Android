package com.dispy.acnhdemo.view.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.dispy.acnhdemo.databinding.ItemCatalogBinding

class CatalogItemAdapter(
    private val values: Array<String>
) : RecyclerView.Adapter<CatalogItemAdapter.ViewHolder>() {

    private lateinit var listener: OnItemClickListener

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemCatalogBinding.inflate(
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

    inner class ViewHolder(private val binding: ItemCatalogBinding) :
            RecyclerView.ViewHolder(binding.root) {
        fun bind(itemName: String) {
            binding.itemName = itemName
            binding.executePendingBindings()
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}