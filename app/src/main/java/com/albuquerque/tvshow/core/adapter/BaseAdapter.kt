package com.albuquerque.tvshow.core.adapter

import android.support.v7.widget.RecyclerView
import com.albuquerque.tvshow.core.view.holder.BaseViewHolder

abstract class BaseAdapter<T, Holder: BaseViewHolder<T>>: RecyclerView.Adapter<Holder>() {

    var items: List<T> = listOf()

    open fun refresh(items: List<T>){
        this.items = items
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(items[position])
    }

}