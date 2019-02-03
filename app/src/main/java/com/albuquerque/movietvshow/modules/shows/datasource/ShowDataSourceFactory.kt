package com.albuquerque.movietvshow.modules.shows.datasource

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import com.albuquerque.movietvshow.modules.shows.enum.TypeCategory
import com.albuquerque.movietvshow.modules.shows.model.Show

class ShowDataSourceFactory(private val categoria: TypeCategory): DataSource.Factory<Int, Show>() {

    val showLiveDataSource = MutableLiveData<ShowDataSource>()

    override fun create(): DataSource<Int, Show> {
        val showDataSource = ShowDataSource(categoria)
        showLiveDataSource.postValue(showDataSource)

        return showDataSource
    }

}