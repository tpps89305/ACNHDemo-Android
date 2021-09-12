package com.dispy.acnhdemo.view.adapter

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dispy.acnhdemo.databinding.ItemCommonBinding
import com.dispy.acnhdemo.model.ArrayHandler
import com.dispy.acnhdemo.model.TagsDecoration
import com.dispy.acnhdemo.model.bean.CommonItem
import com.dispy.acnhdemo.model.bean.Fish
import com.dispy.acnhdemo.model.bean.SeaCreature

class CommonAdapter(
    private val commonItems: ArrayList<CommonItem>
) : RecyclerView.Adapter<CommonAdapter.ViewHolder>(), Filterable {

    private var listener: OnItemClickListener? = null
    private val commonItemsFiltered = ArrayList<CommonItem>()

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemCommonBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = commonItemsFiltered[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            listener?.onItemClick(holder.itemView, item.fileName)
        }

        with(holder.listTags) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = TagsAdapter(item.tags)
            if (itemDecorationCount == 0)
                addItemDecoration(TagsDecoration())
        }

    }

    override fun getItemCount(): Int = commonItemsFiltered.size

    @JvmName("swapFishItems")
    fun swapItems(newItems: List<Fish>) {
        val commonItems = ArrayHandler.parseFishesToCommonItems(newItems)

        this.commonItems.addAll(commonItems)
        commonItemsFiltered.addAll(commonItems)
        notifyDataSetChanged()
    }

    @JvmName("swapSeaCreatureItems")
    fun swapItems(newItems: List<SeaCreature>) {
        val commonItems = ArrayHandler.parseSeaCreaturesToCommonItems(newItems)

        this.commonItems.addAll(commonItems)
        commonItemsFiltered.addAll(commonItems)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemCommonBinding) :
        RecyclerView.ViewHolder(binding.root) {

        lateinit var listTags: RecyclerView

        fun bind(item: CommonItem) {
            binding.item = item
            listTags = binding.listTag
            binding.executePendingBindings()
        }
    }

    interface OnItemClickListener {
        fun onItemClick(view: View, fileName: String)
    }

    override fun getFilter(): Filter {
        return mFilter
    }

    private var mFilter: Filter = object : Filter() {

        override fun performFiltering(constraint: CharSequence): FilterResults {
            val filteredList = ArrayList<CommonItem>()
            if (TextUtils.isEmpty(constraint)) {
                filteredList.addAll(commonItems)
            } else {
                for (commonItem in commonItems) {
                    if (commonItem.name.contains(constraint)) {
                        filteredList.add(commonItem)
                    }
                }
            }
            val filterResults = FilterResults()
            filterResults.values = filteredList
            return filterResults
        }

        override fun publishResults(constraint: CharSequence, results: FilterResults) {
            commonItemsFiltered.clear()
            commonItemsFiltered.addAll(results.values as Collection<CommonItem>)
            notifyDataSetChanged()
        }
    }
}