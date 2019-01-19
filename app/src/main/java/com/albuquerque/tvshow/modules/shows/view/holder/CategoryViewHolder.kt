package com.albuquerque.tvshow.modules.shows.view.holder

import android.view.View
import com.albuquerque.tvshow.core.view.holder.BaseViewHolder
import com.albuquerque.tvshow.modules.shows.adapter.MediaAdapter
import com.albuquerque.tvshow.modules.shows.model.Category
import kotlinx.android.synthetic.main.item_category_list.view.*

class CategoryViewHolder(view: View): BaseViewHolder<Category>(view){

    private var mediaAdapter = MediaAdapter()

    override fun bind(item: Category) {
        mediaAdapter.refresh(item.shows)

        with(itemView){
            titleCategory.text = item.name
            rvCategory.adapter = mediaAdapter
        }
    }


}