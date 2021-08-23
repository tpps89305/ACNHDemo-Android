package com.dispy.acnhdemo.view

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.dispy.acnhdemo.databinding.ItemFishBinding
import com.dispy.acnhdemo.model.bean.Fish

class FishesAdapter(private val fishes: ArrayList<Fish>
) : RecyclerView.Adapter<FishesAdapter.ViewHolder>(), Filterable {

    private lateinit var listener: OnItemClickListener
    private val fishesFiltered = ArrayList<Fish>()

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

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
        val item = fishesFiltered[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            listener.onItemClick(holder.itemView, position, item)
        }
    }

    override fun getItemCount(): Int = fishesFiltered.size

    fun swapItems(newItems: List<Fish>) {
        fishes.addAll(newItems)
        fishesFiltered.addAll(newItems)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemFishBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(fish: Fish) {
            binding.fish = fish
            binding.executePendingBindings()
        }
    }

    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int, fish: Fish)
    }

    override fun getFilter(): Filter {
        return mFilter
    }

    private var mFilter: Filter = object : Filter() {

        override fun performFiltering(constraint: CharSequence): FilterResults {
            val filteredList = ArrayList<Fish>()
            if (TextUtils.isEmpty(constraint)) {
                filteredList.addAll(fishes)
            } else {
                for (villager in fishes) {
                    if (villager.name.nameTWzh.contains(constraint)) {
                        filteredList.add(villager)
                    }
                }
            }
            val filterResults = FilterResults()
            filterResults.values = filteredList
            return filterResults
        }

        override fun publishResults(constraint: CharSequence, results: FilterResults) {
            fishesFiltered.clear()
            fishesFiltered.addAll(results.values as Collection<Fish>)
            notifyDataSetChanged()
        }
    }
}