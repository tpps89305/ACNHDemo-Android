package com.dispy.acnhdemo.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dispy.acnhdemo.databinding.ItemFishBinding
import com.dispy.acnhdemo.model.bean.Fish

class FishesAdapter(
    private val fishes: ArrayList<Fish>
) : RecyclerView.Adapter<FishesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemFishBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = fishes[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = fishes.size

    fun swapItems(newItems: List<Fish>) {
        fishes.addAll(newItems)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemFishBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(fish: Fish) {
            binding.fish = fish
            binding.executePendingBindings()
        }
    }
}