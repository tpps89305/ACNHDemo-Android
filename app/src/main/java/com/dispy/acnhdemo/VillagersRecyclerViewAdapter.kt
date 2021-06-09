package com.dispy.acnhdemo

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.dispy.acnhdemo.databinding.ItemVillagersBinding

import com.dispy.acnhdemo.bean.Villager

class VillagersRecyclerViewAdapter(private val villagers: ArrayList<Villager>)
    : RecyclerView.Adapter<VillagersRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemVillagersBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = villagers[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = villagers.size

    fun swapItems(newItems: List<Villager>) {
        villagers.addAll(newItems)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemVillagersBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(villager: Villager) {
            binding.villager = villager
            binding.executePendingBindings()
        }
    }
}