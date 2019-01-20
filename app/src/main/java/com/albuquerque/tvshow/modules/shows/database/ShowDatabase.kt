package com.albuquerque.tvshow.modules.shows.database

import com.albuquerque.tvshow.core.database.BaseDatabase
import com.albuquerque.tvshow.modules.shows.model.Show
import io.realm.Realm

object ShowDatabase: BaseDatabase() {

    fun removeFavorite(showId: Int){
        Realm.getDefaultInstance().use { realm ->
            realm.beginTransaction()
            realm.where(Show::class.java).equalTo(Show::id.name, showId).findFirst()?.deleteFromRealm()
            realm.commitTransaction()
        }
    }

}