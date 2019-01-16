package com.albuquerque.tvshow.modules.shows.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.albuquerque.tvshow.R
import com.albuquerque.tvshow.core.adapter.BaseAdapter
import com.albuquerque.tvshow.core.view.holder.BaseViewHolder
import com.albuquerque.tvshow.modules.shows.model.CategoryShow
import com.albuquerque.tvshow.modules.shows.view.holder.CategoryListShowViewHolder

class CategoryListShowAdapter: BaseAdapter<CategoryShow, BaseViewHolder<CategoryShow>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<CategoryShow> {
        return CategoryListShowViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_category_list, parent, false))
    }

}