package com.albuquerque.tvshow.core.view.activity

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.albuquerque.tvshow.R
import com.albuquerque.tvshow.modules.auth.view.activity.SettingsActivity
import org.jetbrains.anko.startActivity

abstract class BaseActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){

            android.R.id.home -> {
                onBackPressed()
                true
            }

            R.id.menuSettings -> {
                startActivity<SettingsActivity>()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

}