package com.albuquerque.tvshow.modules.shows.view.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.albuquerque.tvshow.R
import com.albuquerque.tvshow.core.view.BaseActivity
import com.albuquerque.tvshow.modules.viewmodel.AuthViewModel
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : BaseActivity() {

    private lateinit var authViewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        authViewModel = ViewModelProviders.of(this).get(AuthViewModel::class.java)
        subscribeUI()

    }

    private fun subscribeUI() {

        with(authViewModel){

            getLoggedUser().observe(this@HomeActivity, Observer { user ->
                user?.let {
                    nome.text = it.name
                    username.text = it.username
                }
            })

        }

    }
}
