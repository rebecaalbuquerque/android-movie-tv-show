package com.albuquerque.tvshow.core.database

import io.realm.Realm
import io.realm.RealmObject

abstract class BaseDatabase {

    fun <T: RealmObject> saveOrUpdate(realmObject: T){

        Realm.getDefaultInstance().use { realm ->
            realm.beginTransaction()
            realm.copyToRealmOrUpdate(realmObject)
            realm.commitTransaction()
        }

    }

    fun <T: RealmObject> salveOrUpdateAll( realmObject: MutableList<T>, action: ((real: Realm) -> Unit)? = null,onNext: () -> Unit ){

        Realm.getDefaultInstance().executeTransactionAsync(
                Realm.Transaction { realm ->
                    realm.copyToRealmOrUpdate(realmObject)
                    action?.invoke(realm)
                }, Realm.Transaction.OnSuccess(onNext)
        )

    }

    inline fun <reified T: RealmObject> deleteAll(){

        Realm.getDefaultInstance().use { realm ->
            realm.beginTransaction()
            realm.delete(T::class.java)
            realm.commitTransaction()
        }

    }

}