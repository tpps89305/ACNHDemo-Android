package com.dispy.acnhdemo.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dispy.acnhdemo.databinding.ItemTitleContentBinding
import com.dispy.acnhdemo.model.bean.TitleContentPair

class TitleContentAdapter(private val arrayTitleContent: ArrayList<TitleContentPair>) :
    RecyclerView.Adapter<TitleContentAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TitleContentAdapter.ViewHolder {
        return ViewHolder(
            ItemTitleContentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TitleContentAdapter.ViewHolder, position: Int) {
        holder.bind(arrayTitleContent[position])
    }

    override fun getItemCount(): Int = arrayTitleContent.size

    fun swapItems(newItems: List<TitleContentPair>) {
        arrayTitleContent.addAll(newItems)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemTitleContentBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(pair: TitleContentPair) {
                binding.pair = pair
                binding.executePendingBindings()
            }
    }

}