package com.albuquerque.tvshow.modules.shows.view.holder

import android.view.View
import com.albuquerque.tvshow.R
import com.albuquerque.tvshow.core.utils.GlideApp
import com.albuquerque.tvshow.core.view.holder.BaseViewHolder
import com.albuquerque.tvshow.modules.shows.event.OnShowClicked
import com.albuquerque.tvshow.modules.shows.model.Show
import kotlinx.android.synthetic.main.item_show.view.*
import org.greenrobot.eventbus.EventBus

class MediaViewHolder(view: View): BaseViewHolder<Show>(view){

    private var isGrid = false

    override fun bind(item: Show) {
        with(itemView){
            titleShow.text = item.name

            GlideApp
                    .with(context)
                    .load(item.posterPath)
                    .placeholder(R.drawable.placeholder_image)
                    .into(posterShow)

            average.text = item.average.toString()

            if(!isGrid){
                posterShow.setOnClickListener {
                    EventBus.getDefault().post(OnShowClicked(item))
                }
            }

        }
    }

    fun bind(item: Show, isGrid: Boolean){
        this.isGrid = isGrid
        bind(item)
    }
}