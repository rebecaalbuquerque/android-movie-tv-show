package com.albuquerque.tvshow.core.extensions

import android.support.v7.widget.RecyclerView
import com.rohit.recycleritemclicksupport.RecyclerItemClickSupport

fun RecyclerView.setOnItemClickListener(onClick: (recyclerView: RecyclerView, position: Int, viewClicked: RecyclerView.ViewHolder?) -> Unit) {
    RecyclerItemClickSupport.addTo(this).setOnItemClickListener { recyclerView, position, v ->
        recyclerView?.adapter?.let { adapter ->
            if (position != RecyclerView.NO_POSITION && position < adapter.itemCount) {
                onClick(this, position, recyclerView.findViewHolderForAdapterPosition(position))
            }
        }
    }
}

fun RecyclerView.setOnItemLongClickListener(onLongClick: (recyclerView: RecyclerView, position: Int, viewClicked: RecyclerView.ViewHolder?) -> Unit) {
    RecyclerItemClickSupport.addTo(this).setOnItemLongClickListener { recyclerView, position, v ->
        recyclerView?.adapter?.let { adapter ->
            if (position != RecyclerView.NO_POSITION && position < adapter.itemCount) {
                onLongClick(this, position, recyclerView.findViewHolderForAdapterPosition(position))
            }
        }
        true
    }
}