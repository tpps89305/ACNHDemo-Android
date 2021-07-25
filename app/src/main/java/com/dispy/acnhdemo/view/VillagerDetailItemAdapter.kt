package com.dispy.acnhdemo.view

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dispy.acnhdemo.model.bean.VillagerDetailItem
import com.dispy.acnhdemo.databinding.ItemVillagerAvatarBinding
import com.dispy.acnhdemo.databinding.ItemVillagerDetailBinding

class VillagerDetailItemAdapter(private val villagerDetailItems: ArrayList<VillagerDetailItem>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val VIEW_TYPE_AVATAR = 0
    private val VIEW_TYPE_DETAIL = 1
    private lateinit var imageUrl: String
    private var textColorHex = Color.parseColor("#000000")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_AVATAR) {
            AvatarViewHolder(ItemVillagerAvatarBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ))
        } else {
            DetailViewHolder(ItemVillagerDetailBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), textColorHex)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            VIEW_TYPE_AVATAR
        } else {
            VIEW_TYPE_DETAIL
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is DetailViewHolder) {
            val item = villagerDetailItems[position - 1]
            holder.bind(item)
        } else if (holder is AvatarViewHolder) {
            val imageUrl = this.imageUrl
            holder.bind(imageUrl)
        }
    }

    override fun getItemCount(): Int = villagerDetailItems.size + 1

    fun swapItems(newItems: List<VillagerDetailItem>) {
        villagerDetailItems.addAll(newItems)
        notifyDataSetChanged()
    }

    fun swapImageUrl(imageUrl: String) {
        this.imageUrl = imageUrl
        notifyDataSetChanged()
    }

    fun swapTextColor(textColorHex: Int) {
        this.textColorHex = textColorHex
        notifyDataSetChanged()
    }

    inner class AvatarViewHolder(private val binding: ItemVillagerAvatarBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(imageUrl: String) {
            binding.imageUrl = imageUrl
            binding.executePendingBindings()
        }
    }

    inner class DetailViewHolder(private val binding: ItemVillagerDetailBinding, private val textColorHex: Int) :
            RecyclerView.ViewHolder(binding.root) {
        fun bind(villagerDetailItem: VillagerDetailItem) {
            binding.textColor = textColorHex
            binding.villagerDetailItem = villagerDetailItem
            binding.executePendingBindings()
        }
    }
}