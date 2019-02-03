package com.albuquerque.movietvshow.modules.shows.datasource

import android.arch.paging.PageKeyedDataSource
import com.albuquerque.movietvshow.modules.shows.business.ShowsBusiness
import com.albuquerque.movietvshow.modules.shows.enum.TypeCategory
import com.albuquerque.movietvshow.modules.shows.enum.TypeCategory.*
import com.albuquerque.movietvshow.modules.shows.event.OnErrorPagedList
import com.albuquerque.movietvshow.modules.shows.model.Show
import org.greenrobot.eventbus.EventBus

class ShowDataSource(private val categoria: TypeCategory): PageKeyedDataSource<Int, Show>() {

    private var page = 1

    // é chamado 1 vez, para carregar os dados inicias
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Show>) {

        when(categoria){
            FAVORITAS -> {

            }

            EM_EXIBICAO -> {
                ShowsBusiness.getAiringTodayFromAPI(page,
                        { callback.onResult(it, null, page + 1) },
                        { EventBus.getDefault().post(OnErrorPagedList(it)) }
                )
            }

            POPULARES -> {
                ShowsBusiness.getPopularFromAPI(page,
                        { callback.onResult(it, null, page + 1) },
                        { EventBus.getDefault().post(OnErrorPagedList(it)) }
                )
            }

            MELHORES_AVALIADAS->{
                ShowsBusiness.getTopRatedFromAPI(page,
                        { callback.onResult(it, null, page + 1) },
                        { EventBus.getDefault().post(OnErrorPagedList(it)) }
                )
            }
        }

    }

    // carrega a próxima página
    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Show>) {

        when(categoria){
            FAVORITAS -> {

            }

            EM_EXIBICAO -> {
                ShowsBusiness.getAiringTodayFromAPI(params.key,
                        { callback.onResult(it, params.key + 1) },
                        { EventBus.getDefault().post(OnErrorPagedList(it)) }
                )
            }

            POPULARES -> {
                ShowsBusiness.getPopularFromAPI(params.key,
                        { callback.onResult(it, params.key + 1) },
                        { EventBus.getDefault().post(OnErrorPagedList(it)) }
                )
            }

            MELHORES_AVALIADAS->{
                ShowsBusiness.getTopRatedFromAPI(params.key,
                        { callback.onResult(it, params.key + 1) },
                        { EventBus.getDefault().post(OnErrorPagedList(it)) }
                )
            }
        }

    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Show>) {}
}