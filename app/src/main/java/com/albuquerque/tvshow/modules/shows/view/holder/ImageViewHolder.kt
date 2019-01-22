package com.albuquerque.tvshow.modules.shows.view.holder

import android.view.View
import com.albuquerque.tvshow.R
import com.albuquerque.tvshow.core.utils.GlideApp
import com.albuquerque.tvshow.core.view.holder.BaseViewHolder
import com.albuquerque.tvshow.modules.shows.model.Image
import kotlinx.android.synthetic.main.item_picture.view.*

class ImageViewHolder(view: View): BaseViewHolder<Image>(view){

    override fun bind(item: Image) {
        with(itemView){
            GlideApp
                    .with(context)
                    .load(item.url)
                    .placeholder(R.drawable.placeholder_image)
                    .into(picture)
        }
    }

}