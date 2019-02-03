package com.albuquerque.movietvshow.core.extensions

import com.albuquerque.movietvshow.core.livedata.RealmLiveData
import io.realm.RealmModel
import io.realm.RealmResults

fun <T : RealmModel> RealmResults<T>.asLiveData(): RealmLiveData<T> {
    return RealmLiveData(this)
}