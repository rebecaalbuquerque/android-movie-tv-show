package com.albuquerque.tvshow.modules.shows.adapter

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import com.albuquerque.tvshow.R
import com.albuquerque.tvshow.modules.shows.model.Show
import com.albuquerque.tvshow.modules.shows.view.holder.MediaViewHolder

class PagedShowsAdapter: PagedListAdapter<Show, MediaViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Show>() {
            override fun areItemsTheSame(oldItem: Show, newItem: Show): Boolean {
                return oldItem.id== newItem.id
            }

            override fun areContentsTheSame(oldItem: Show, newItem: Show): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MediaViewHolder {
        return MediaViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_show_grid, parent, false))
    }

    override fun onBindViewHolder(holder: MediaViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

}