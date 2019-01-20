package com.albuquerque.tvshow.modules.shows.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.albuquerque.tvshow.R
import com.albuquerque.tvshow.core.adapter.BaseAdapter
import com.albuquerque.tvshow.core.view.holder.BaseViewHolder
import com.albuquerque.tvshow.modules.shows.model.Image
import com.albuquerque.tvshow.modules.shows.view.holder.ImageViewHolder

class ImageAdapter: BaseAdapter<Image, BaseViewHolder<Image>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Image> {
        return ImageViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_picture, parent, false))
    }

}