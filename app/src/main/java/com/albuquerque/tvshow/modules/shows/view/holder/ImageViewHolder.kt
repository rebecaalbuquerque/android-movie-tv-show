package com.albuquerque.tvshow.modules.shows.view.holder

import android.view.View
import com.albuquerque.tvshow.core.view.holder.BaseViewHolder
import com.albuquerque.tvshow.modules.shows.model.Image
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_picture.view.*

class ImageViewHolder(view: View): BaseViewHolder<Image>(view){

    override fun bind(item: Image) {
        with(itemView){ Picasso.get().load(item.url).into(picture) }
    }

}