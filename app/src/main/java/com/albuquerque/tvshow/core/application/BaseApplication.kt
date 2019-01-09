package com.albuquerque.tvshow.core.application

import android.app.Application
import com.albuquerque.tvshow.BuildConfig
import io.realm.Realm
import io.realm.RealmConfiguration

class BaseApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        setupRealm()
    }

    private fun setupRealm() {
        Realm.init(this)

        val realmConfigBuilder = RealmConfiguration.Builder()
        realmConfigBuilder.schemaVersion(BuildConfig.VERSION_CODE.toLong())
        realmConfigBuilder.deleteRealmIfMigrationNeeded()

        Realm.setDefaultConfiguration(realmConfigBuilder.build())
    }

}