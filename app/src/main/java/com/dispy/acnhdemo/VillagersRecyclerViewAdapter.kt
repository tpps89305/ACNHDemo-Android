package com.dispy.acnhdemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.dispy.acnhdemo.bean.Villager
import com.dispy.acnhdemo.databinding.ItemVillagersBinding

class VillagersRecyclerViewAdapter(private val villagers: ArrayList<Villager>) :
    RecyclerView.Adapter<VillagersRecyclerViewAdapter.ViewHolder>() {

    private lateinit var listener: OnItemClickListener

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemVillagersBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = villagers[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            listener.onItemClick(holder.itemView, position, item)
        }
    }

    override fun getItemCount(): Int = villagers.size

    fun swapItems(newItems: List<Villager>) {
        villagers.addAll(newItems)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemVillagersBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(villager: Villager) {
            binding.villager = villager
            ViewCompat.setTransitionName(binding.root, villager.fileName)
            binding.executePendingBindings()
        }
    }

    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int, villager: Villager)
    }

}