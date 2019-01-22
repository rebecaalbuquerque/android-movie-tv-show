package com.albuquerque.tvshow.modules.shows.view.holder

import android.view.View
import com.albuquerque.tvshow.R
import com.albuquerque.tvshow.core.utils.GlideApp
import com.albuquerque.tvshow.core.view.holder.BaseViewHolder
import com.albuquerque.tvshow.modules.shows.model.Channel
import kotlinx.android.synthetic.main.item_channel.view.*

class ChannelViewHolder(view: View): BaseViewHolder<Channel>(view) {

    override fun bind(item: Channel) {
        with(itemView){
            GlideApp
                    .with(context)
                    .load(item.logo)
                    .placeholder(R.drawable.placeholder_image)
                    .into(channelPicture)
        }
    }

}