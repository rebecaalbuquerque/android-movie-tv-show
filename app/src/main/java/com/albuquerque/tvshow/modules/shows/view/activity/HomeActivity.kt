package com.albuquerque.tvshow.modules.shows.view.activity

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.Menu
import com.albuquerque.tvshow.R
import com.albuquerque.tvshow.core.view.activity.BaseActivity
import com.albuquerque.tvshow.modules.auth.viewmodel.AuthViewModel
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : BaseActivity() {

    private lateinit var authViewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        authViewModel = ViewModelProviders.of(this).get(AuthViewModel::class.java)
        subscribeUI()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        return true
    }

    private fun subscribeUI() {

        with(authViewModel){

        }

    }
}
