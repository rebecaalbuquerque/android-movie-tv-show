package com.albuquerque.tvshow.modules.shows.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.albuquerque.tvshow.R
import com.albuquerque.tvshow.core.adapter.BaseAdapter
import com.albuquerque.tvshow.core.view.holder.BaseViewHolder
import com.albuquerque.tvshow.modules.shows.model.Show
import com.albuquerque.tvshow.modules.shows.view.holder.MediaViewHolder

class MediaAdapter: BaseAdapter<Show, BaseViewHolder<Show>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Show> {
        return MediaViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_show, parent, false))
    }

}