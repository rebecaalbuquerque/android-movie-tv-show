package com.albuquerque.tvshow.modules.shows.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.albuquerque.tvshow.R
import com.albuquerque.tvshow.core.adapter.BaseAdapter
import com.albuquerque.tvshow.core.view.holder.BaseViewHolder
import com.albuquerque.tvshow.modules.shows.model.Category
import com.albuquerque.tvshow.modules.shows.view.holder.CategoryViewHolder

class CategoryAdapter: BaseAdapter<Category, BaseViewHolder<Category>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Category> {
        return CategoryViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_category_list, parent, false))
    }

}