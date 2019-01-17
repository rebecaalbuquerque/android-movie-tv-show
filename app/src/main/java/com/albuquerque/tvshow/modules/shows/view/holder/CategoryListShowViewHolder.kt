package com.albuquerque.tvshow.modules.shows.view.holder

import android.view.View
import com.albuquerque.tvshow.core.view.holder.BaseViewHolder
import com.albuquerque.tvshow.modules.shows.adapter.MediaAdapter
import com.albuquerque.tvshow.modules.shows.model.CategoryShow
import kotlinx.android.synthetic.main.item_category_list.view.*

class CategoryListShowViewHolder(view: View): BaseViewHolder<CategoryShow>(view){

    private var mediaAdapter = MediaAdapter()

    override fun bind(item: CategoryShow) {
        mediaAdapter.refresh(item.shows)

        with(itemView){
            titleCategory.text = item.name
            rvCategory.adapter = mediaAdapter
        }
    }

}