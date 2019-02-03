package com.albuquerque.movietvshow.modules.shows.view.holder

import android.view.View
import com.albuquerque.movietvshow.core.view.holder.BaseViewHolder
import com.albuquerque.movietvshow.modules.shows.adapter.MediaAdapter
import com.albuquerque.movietvshow.modules.shows.event.OnCategorySeeMore
import com.albuquerque.movietvshow.modules.shows.model.Category
import kotlinx.android.synthetic.main.item_category.view.*
import org.greenrobot.eventbus.EventBus

class CategoryViewHolder(view: View): BaseViewHolder<Category>(view){

    private var mediaAdapter = MediaAdapter()

    override fun bind(item: Category) {
        mediaAdapter.refresh(item.shows)

        with(itemView){
            titleCategory.text = item.type.value
            rvCategory.adapter = mediaAdapter

            seeMore.setOnClickListener {
                EventBus.getDefault().post(OnCategorySeeMore(item.type))
            }

        }

    }

}