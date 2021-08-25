package com.dispy.acnhdemo.view.adapter

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.dispy.acnhdemo.databinding.ItemSongBinding
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
    }

    override fun getItemCount(): Int = songsFiltered.size

    fun swapItems(newItems: List<Song>) {
        songs.addAll(newItems)
        songsFiltered.addAll(newItems)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemSongBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(song: Song) {
            binding.song = song
            binding.sourceInfo = parseSourceInfo(song)
            binding.priceInfo = parsePriceInfo(song)
            binding.executePendingBindings()
        }

        private fun parseSourceInfo(song: Song): String {
            var result = "K.K. concert"
            if (song.isOrderable) {
                result += ", Nook Shopping Catalog"
            }
            return result
        }

        private fun parsePriceInfo(song: Song): String {
            var result = ""
            if (song.buyPrice != null) {
                result += "Buy: ${song.buyPrice}, "
            }
            result += "Sell: ${song.sellPrice}"
            return result
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