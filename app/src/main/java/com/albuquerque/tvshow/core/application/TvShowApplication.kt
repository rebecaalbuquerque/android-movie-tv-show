package com.albuquerque.tvshow.core.application

import android.app.Application
import com.albuquerque.tvshow.BuildConfig
import com.albuquerque.tvshow.BuildConfig.DEBUG
import com.facebook.stetho.Stetho
import com.uphyca.stetho_realm.RealmInspectorModulesProvider
import io.realm.Realm
import io.realm.RealmConfiguration

class TvShowApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        setupRealm()
        setupStetho()
    }

    private fun setupRealm() {
        Realm.init(this)

        val realmConfigBuilder = RealmConfiguration.Builder()
        realmConfigBuilder.schemaVersion(BuildConfig.VERSION_CODE.toLong())
        realmConfigBuilder.deleteRealmIfMigrationNeeded()

        Realm.setDefaultConfiguration(realmConfigBuilder.build())
    }

    private fun setupStetho() {
        if (DEBUG) {
            val realmInspector = RealmInspectorModulesProvider.builder(this)
                    .withDeleteIfMigrationNeeded(true)
                    .build()
            Stetho.initialize(Stetho.newInitializerBuilder(this)
                    .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                    .enableWebKitInspector(realmInspector)
                    .build())
        }
    }

}