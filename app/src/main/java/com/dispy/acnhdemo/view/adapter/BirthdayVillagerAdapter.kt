package com.dispy.acnhdemo.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dispy.acnhdemo.databinding.ItemBirthdayVillagerBinding
import com.dispy.acnhdemo.model.bean.Villager

/**
 * ACNH Demo
 * https://github.com/tpps89305/ACNHDemo-Android
 *
 * Created by Dispy on 2021/12/06
 * tpps89305@hotmail.com
 */
class BirthdayVillagerAdapter: RecyclerView.Adapter<BirthdayVillagerAdapter.ViewHolder>() {

    private var listener: OnItemClickListener? = null
    private val list: ArrayList<Villager> = arrayListOf()

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemBirthdayVillagerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
        holder.itemView.setOnClickListener {
            listener?.onItemClick(list[position])
        }
    }

    override fun getItemCount(): Int = list.count()

    fun submitList(newList: List<Villager>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemBirthdayVillagerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(villager: Villager) {
            binding.villager = villager
            binding.executePendingBindings()
        }
    }

    interface OnItemClickListener {
        fun onItemClick(villager: Villager)
    }
}