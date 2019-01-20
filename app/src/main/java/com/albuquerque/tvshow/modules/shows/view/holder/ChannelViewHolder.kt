package com.albuquerque.tvshow.modules.shows.view.holder

import android.view.View
import com.albuquerque.tvshow.core.view.holder.BaseViewHolder
import com.albuquerque.tvshow.modules.shows.model.Channel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_channel.view.*

class ChannelViewHolder(view: View): BaseViewHolder<Channel>(view) {

    override fun bind(item: Channel) {
        with(itemView){ Picasso.get().load(item.logo).into(channelPicture) }
    }

}