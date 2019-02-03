package com.albuquerque.movietvshow.modules.shows.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.albuquerque.movietvshow.R
import com.albuquerque.movietvshow.core.adapter.BaseAdapter
import com.albuquerque.movietvshow.core.view.holder.BaseViewHolder
import com.albuquerque.movietvshow.modules.shows.model.Show
import com.albuquerque.movietvshow.modules.shows.view.holder.MediaViewHolder

class MediaAdapter: BaseAdapter<Show, BaseViewHolder<Show>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Show> {
        return MediaViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_show, parent, false))
    }

}