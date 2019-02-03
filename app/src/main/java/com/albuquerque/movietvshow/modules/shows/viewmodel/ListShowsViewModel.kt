package com.albuquerque.movietvshow.modules.shows.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.albuquerque.movietvshow.core.livedata.SingleLiveEvent
import com.albuquerque.movietvshow.modules.shows.business.ShowsBusiness
import com.albuquerque.movietvshow.modules.shows.datasource.ShowDataSource
import com.albuquerque.movietvshow.modules.shows.datasource.ShowDataSourceFactory
import com.albuquerque.movietvshow.modules.shows.enum.TypeCategory
import com.albuquerque.movietvshow.modules.shows.model.Show

class ListShowsViewModel(val category: TypeCategory): ViewModel() {

    private var showsPaged: LiveData<PagedList<Show>> = MutableLiveData()
    private var liveDataSource: LiveData<ShowDataSource> = MutableLiveData()

    var onError = SingleLiveEvent<String>()

    fun getShows(): LiveData<PagedList<Show>>{
        val showDataSourceFactory = ShowDataSourceFactory(category)

        liveDataSource = showDataSourceFactory.showLiveDataSource

        showsPaged = LivePagedListBuilder(showDataSourceFactory, ShowsBusiness.getPagedListConfig()).build()
        return showsPaged
    }

}