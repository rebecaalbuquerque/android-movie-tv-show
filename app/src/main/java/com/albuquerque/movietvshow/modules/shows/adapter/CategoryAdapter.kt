package com.albuquerque.movietvshow.modules.shows.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.albuquerque.movietvshow.R
import com.albuquerque.movietvshow.core.adapter.BaseAdapter
import com.albuquerque.movietvshow.core.view.holder.BaseViewHolder
import com.albuquerque.movietvshow.modules.shows.model.Category
import com.albuquerque.movietvshow.modules.shows.view.holder.CategoryViewHolder

class CategoryAdapter: BaseAdapter<Category, BaseViewHolder<Category>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Category> {
        return CategoryViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false))
    }

}