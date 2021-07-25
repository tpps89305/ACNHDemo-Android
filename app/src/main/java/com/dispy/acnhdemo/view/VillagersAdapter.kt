package com.dispy.acnhdemo.view

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.dispy.acnhdemo.model.bean.Villager
import com.dispy.acnhdemo.databinding.ItemVillagersBinding

class VillagersAdapter(private val villagers: ArrayList<Villager>) :
    RecyclerView.Adapter<VillagersAdapter.ViewHolder>(), Filterable {

    private lateinit var listener: OnItemClickListener
    private val villagersFiltered = ArrayList<Villager>()

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
        val item = villagersFiltered[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            listener.onItemClick(holder.itemView, position, item)
        }
    }

    override fun getItemCount(): Int = villagersFiltered.size

    fun swapItems(newItems: List<Villager>) {
        villagers.addAll(newItems)
        villagersFiltered.addAll(newItems)
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

    override fun getFilter(): Filter {
        return mFilter
    }

    private var mFilter: Filter = object : Filter() {

        override fun performFiltering(constraint: CharSequence): FilterResults {
            val filteredList = ArrayList<Villager>()
            if (TextUtils.isEmpty(constraint)) {
                filteredList.addAll(villagers)
            } else {
                for (villager in villagers) {
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
            villagersFiltered.clear()
            villagersFiltered.addAll(results.values as Collection<Villager>)
            notifyDataSetChanged()
        }
    }

}