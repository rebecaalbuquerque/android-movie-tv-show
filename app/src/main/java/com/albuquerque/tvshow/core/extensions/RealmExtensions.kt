package com.albuquerque.tvshow.core.extensions

import com.albuquerque.tvshow.core.livedata.RealmLiveData
import io.realm.RealmModel
import io.realm.RealmResults

fun <T : RealmModel> RealmResults<T>.asLiveData(): RealmLiveData<T> {
    return RealmLiveData(this)
}