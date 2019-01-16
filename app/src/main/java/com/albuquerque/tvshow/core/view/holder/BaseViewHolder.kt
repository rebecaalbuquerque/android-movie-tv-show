package com.albuquerque.tvshow.core.view.holder

import android.support.v7.widget.RecyclerView
import android.view.View
import com.albuquerque.tvshow.core.adapter.BindableView

abstract class BaseViewHolder<T>(var view: View): RecyclerView.ViewHolder(view), BindableView<T>