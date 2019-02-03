package com.albuquerque.movietvshow.core.view.holder

import android.support.v7.widget.RecyclerView
import android.view.View
import com.albuquerque.movietvshow.core.adapter.BindableView

abstract class BaseViewHolder<T>(var view: View): RecyclerView.ViewHolder(view), BindableView<T>{
    abstract override fun bind(item: T)
}