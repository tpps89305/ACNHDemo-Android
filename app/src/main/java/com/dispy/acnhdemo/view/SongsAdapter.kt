package com.dispy.acnhdemo.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dispy.acnhdemo.databinding.ItemSongBinding
import com.dispy.acnhdemo.model.bean.Song

class SongsAdapter(
    private val songs: ArrayList<Song>
) : RecyclerView.Adapter<SongsAdapter.ViewHolder>() {

    private lateinit var listener: OnItemClickListener

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
        val item = songs[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            listener.onItemClick(holder.itemView, position, item)
        }
    }

    override fun getItemCount(): Int = songs.size

    fun swapItems(newItems: List<Song>) {
        songs.addAll(newItems)
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
}