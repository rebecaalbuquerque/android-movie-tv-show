package com.albuquerque.tvshow.modules.shows.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.albuquerque.tvshow.R
import com.albuquerque.tvshow.core.adapter.BaseAdapter
import com.albuquerque.tvshow.core.view.holder.BaseViewHolder
import com.albuquerque.tvshow.modules.shows.model.Channel
import com.albuquerque.tvshow.modules.shows.view.holder.ChannelViewHolder

class ChannelAdapter: BaseAdapter<Channel, BaseViewHolder<Channel>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Channel> {
        return ChannelViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_channel, parent, false))
    }

}