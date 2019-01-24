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

    private var imageWidth = 0
    private var imageHeigth = 0
    private var type = CHANNEL

    override fun bind(item: Image) {

        when(type){
            CHANNEL     -> setupImage(item.logo)
            MEDIA_IMAGE -> setupImage(item.url)
        }

    }

    fun bind(item: Image, type: TypeImage, width: Int, height: Int){
        this.type = type
        imageHeigth = height
        imageWidth = width
        bind(item)
    }

    private fun setupImage(url: String){
        with(itemView){

            GlideApp
                    .with(context)
                    .load(url)
                    .placeholder(R.drawable.placeholder_image)
                    .override(imageWidth.toDp(), imageHeigth.toDp())
                    .into(picture)

        }
    }
}