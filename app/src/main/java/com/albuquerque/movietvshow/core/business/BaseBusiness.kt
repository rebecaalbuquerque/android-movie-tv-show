package com.albuquerque.movietvshow.core.business

import android.arch.paging.PagedList

abstract class BaseBusiness{

    companion object {
        const val PAGE_SIZE = 20
    }

    fun getPagedListConfig(): PagedList.Config {
        return PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(PAGE_SIZE)
                .build()
    }

}