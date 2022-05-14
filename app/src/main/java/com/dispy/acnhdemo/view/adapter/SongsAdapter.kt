package com.dispy.acnhdemo.view.adapter

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dispy.acnhdemo.databinding.ItemSongBinding
import com.dispy.acnhdemo.model.TagsDecoration
import com.dispy.acnhdemo.model.bean.Song

class SongsAdapter(
    private val songs: ArrayList<Song>
) : RecyclerView.Adapter<SongsAdapter.ViewHolder>(), Filterable {

    private lateinit var listener: OnItemClickListener
    private val songsFiltered = ArrayList<Song>()

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemSongBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = songsFiltered[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            listener.onItemClick(holder.itemView, position, item)
        }

        with(holder.listSongTags) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = TagsAdapter(parsePriceInfo(item))
            if (itemDecorationCount == 0)
                addItemDecoration(TagsDecoration())
        }

    }

    override fun getItemCount(): Int = songsFiltered.size

    fun swapItems(newItems: List<Song>) {
        songs.addAll(newItems)
        songsFiltered.addAll(newItems)
        notifyDataSetChanged()
    }

    private fun parsePriceInfo(song: Song): ArrayList<String> {
        val tags = ArrayList<String>()
        if (song.buyPrice != null) {
            tags.add("Buy: ${song.buyPrice}")
        }
        tags.add("Sell: ${song.sellPrice}")
        return tags
    }

    inner class ViewHolder(private val binding: ItemSongBinding) :
        RecyclerView.ViewHolder(binding.root) {

        lateinit var listSongTags: RecyclerView

        fun bind(song: Song) {
            binding.song = song
            listSongTags = binding.listSongTag
            binding.executePendingBindings()
        }

    }

    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int, song: Song)
    }

    override fun getFilter(): Filter {
        return mFilter
    }

    private var mFilter: Filter = object : Filter() {

        override fun performFiltering(constraint: CharSequence): FilterResults {
            val filteredList = ArrayList<Song>()
            if (TextUtils.isEmpty(constraint)) {
                filteredList.addAll(songs)
            } else {
                for (song in songs) {
                    if (song.name.nameTWzh.contains(constraint)) {
                        filteredList.add(song)
                    }
                }
            }
            val filterResults = FilterResults()
            filterResults.values = filteredList
            return filterResults
        }

        override fun publishResults(constraint: CharSequence, results: FilterResults) {
            songsFiltered.clear()
            songsFiltered.addAll(results.values as Collection<Song>)
            notifyDataSetChanged()
        }
    }
}