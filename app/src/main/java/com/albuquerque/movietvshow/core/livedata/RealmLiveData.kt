package com.albuquerque.movietvshow.core.livedata

import android.arch.lifecycle.LiveData
import io.realm.Realm
import io.realm.RealmChangeListener
import io.realm.RealmModel
import io.realm.RealmResults

class RealmLiveData<T : RealmModel>(
        private val realmResults: RealmResults<T>
) : LiveData<MutableList<T>>() {

    private val realm: Realm by lazy { Realm.getDefaultInstance() }
    private val realmChangeListener = RealmChangeListener<RealmResults<T>> { results ->
        value = realm.copyFromRealm(results)
    }

    override fun onActive() {
        super.onActive()
        realmResults.addChangeListener(realmChangeListener)
    }

    override fun onInactive() {
        super.onInactive()
        realmResults.removeChangeListener(realmChangeListener)
    }

}