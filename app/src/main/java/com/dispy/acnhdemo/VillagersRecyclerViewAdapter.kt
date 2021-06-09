package com.dispy.acnhdemo

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.dispy.acnhdemo.function.Villager

class VillagersRecyclerViewAdapter(private val villagers: ArrayList<Villager>)
    : RecyclerView.Adapter<VillagersRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_villagers, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = villagers[position]
        holder.idView.text = item.fileName
        holder.contentView.text = item.name.nameTWzh
    }

    override fun getItemCount(): Int = villagers.size

    fun swapItems(newItems: List<Villager>) {
        villagers.addAll(newItems)
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val idView: TextView = view.findViewById(R.id.item_number)
        val contentView: TextView = view.findViewById(R.id.content)

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }
}