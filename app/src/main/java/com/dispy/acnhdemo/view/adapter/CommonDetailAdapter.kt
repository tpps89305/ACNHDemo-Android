package com.dispy.acnhdemo.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dispy.acnhdemo.databinding.ItemCommonAvatarBinding
import com.dispy.acnhdemo.databinding.ItemTitleContentBinding
import com.dispy.acnhdemo.model.bean.TitleContentPair

/**
 * ACNH Demo
 * https://github.com/tpps89305/ACNHDemo-Android
 *
 * Created by Dispy on 2021/09/26
 * tpps89305@hotmail.com
 */
class CommonDetailAdapter(private val imageUrl: String, private val titleContentPairs: ArrayList<TitleContentPair>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_AVATAR = 0
        private const val VIEW_TYPE_DETAIL = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_AVATAR) {
            AvatarViewHolder(
                ItemCommonAvatarBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ))
        } else {
            DetailViewHolder(
                ItemTitleContentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ))
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
            val item = titleContentPairs[position - 1]
            holder.bind(item)
        } else if (holder is AvatarViewHolder) {
            val imageUrl = this.imageUrl
            holder.bind(imageUrl)
        }
    }

    override fun getItemCount(): Int = titleContentPairs.size + 1

    fun swapItems(newItems: List<TitleContentPair>) {
        titleContentPairs.addAll(newItems)
        notifyDataSetChanged()
    }

    inner class AvatarViewHolder(private val binding: ItemCommonAvatarBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(imageUrl: String) {
            binding.imageUrl = imageUrl
            binding.executePendingBindings()
        }
    }

    inner class DetailViewHolder(private val binding: ItemTitleContentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(pair: TitleContentPair) {
            binding.pair = pair
            binding.executePendingBindings()
        }
    }

}