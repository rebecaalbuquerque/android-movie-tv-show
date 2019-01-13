package com.albuquerque.tvshow.modules.auth.database

import com.albuquerque.tvshow.core.database.BaseDatabase
import com.albuquerque.tvshow.modules.auth.model.User
import io.realm.Realm

object AuthDatabase: BaseDatabase() {

    fun clearDatabase(){

        Realm.getDefaultInstance().use { realm ->

            realm.beginTransaction()
            realm.deleteAll()
            realm.commitTransaction()

        }

    }

    fun getUser(): User? = Realm.getDefaultInstance().where(User::class.java).findFirst()

    fun getSessionId(): String = Realm.getDefaultInstance().where(User::class.java).findFirst()?.sessionId ?: ""
}