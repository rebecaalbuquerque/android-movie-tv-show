package com.albuquerque.tvshow.modules.shows.view.holder

import android.view.View
import com.albuquerque.tvshow.core.view.holder.BaseViewHolder
import com.albuquerque.tvshow.modules.shows.event.OnShowClicked
import com.albuquerque.tvshow.modules.shows.model.Show
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_show.view.*
import org.greenrobot.eventbus.EventBus

class MediaViewHolder(view: View): BaseViewHolder<Show>(view){

    override fun bind(item: Show) {
        with(itemView){
            titleShow.text = item.name
            Picasso.get().load(item.posterPath).into(posterShow)

            posterShow.setOnClickListener {
                EventBus.getDefault().post(OnShowClicked(item))
            }

        }
    }

}