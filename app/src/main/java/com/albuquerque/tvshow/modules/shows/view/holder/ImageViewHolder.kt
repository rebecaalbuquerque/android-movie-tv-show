package com.albuquerque.tvshow.modules.shows.view.holder

import android.view.View
import com.albuquerque.tvshow.R
import com.albuquerque.tvshow.core.extensions.toDp
import com.albuquerque.tvshow.core.utils.GlideApp
import com.albuquerque.tvshow.core.view.holder.BaseViewHolder
import com.albuquerque.tvshow.modules.shows.enum.TypeImage
import com.albuquerque.tvshow.modules.shows.enum.TypeImage.*
import com.albuquerque.tvshow.modules.shows.model.Image
import kotlinx.android.synthetic.main.item_picture.view.*

class ImageViewHolder(view: View): BaseViewHolder<Image>(view){

    private var typeImage = CHANNEL

    override fun bind(item: Image) {

        when(typeImage){
            CHANNEL     -> setupImage(item.logoChannel)
            MEDIA_IMAGE -> setupImage(item.url)
        }

    }

    fun bind(item: Image, type: TypeImage){
        typeImage = type
        bind(item)
    }

    private fun setupImage(url: String){
        with(itemView){

            GlideApp
                    .with(context)
                    .load(url)
                    .override(typeImage.dimension.first.toDp(), typeImage.dimension.second.toDp())
                    .into(picture)

        }
    }
}