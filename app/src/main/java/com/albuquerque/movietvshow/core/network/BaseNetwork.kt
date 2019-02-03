package com.albuquerque.movietvshow.core.network

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.OkHttpClient

abstract class BaseNetwork {

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500"
        const val BASE_GRAVATAR_URL = "https://secure.gravatar.com/avatar/"
        const val API_KEY  = "425f2b57c19b2fbed0815a3e3128c4d3"
        const val LANGUAGE = "pt-br"
    }

    protected fun getRetrofitBuilder(baseUrl: String = BASE_URL): Retrofit.Builder{

        val retrofitBuilder = Retrofit.Builder()

        with(retrofitBuilder){
            baseUrl(baseUrl)
            addConverterFactory(GsonConverterFactory.create())
            addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            client( OkHttpClient.Builder()
                            .addNetworkInterceptor(StethoInterceptor())
                            .build()
            )
        }

        return retrofitBuilder

    }

    protected fun <T, A> doRequest(api: A, onSucess: (response: T) -> Unit, onError: ((error: Throwable) -> Unit)? = null, func: A.() -> Observable<T>): Disposable{

        return api.func()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { response ->
                            onSucess(response)
                        },
                        {
                            //onError(NetworkError((error)))
                            onError?.invoke(it)
                        }
                )

    }


}