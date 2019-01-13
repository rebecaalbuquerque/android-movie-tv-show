package com.albuquerque.tvshow.modules.auth.view.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View.GONE
import android.view.View.VISIBLE
import com.albuquerque.tvshow.R
import com.albuquerque.tvshow.core.extensions.setTitleAndBackButton
import com.albuquerque.tvshow.core.extensions.showError
import com.albuquerque.tvshow.core.view.activity.BaseActivity
import com.albuquerque.tvshow.modules.auth.viewmodel.AuthViewModel
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import kotlinx.android.synthetic.main.activity_settings.*
import org.jetbrains.anko.startActivity

class SettingsActivity : BaseActivity() {

    private lateinit var authViewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        progressSettings.visibility = VISIBLE
        authViewModel = ViewModelProviders.of(this).get(AuthViewModel::class.java)

        subscribeUI()
        setupToolbar()
        setupButtonsClick()
    }

    private fun setupButtonsClick() {

        logout.setOnClickListener {
            progressSettings.visibility = VISIBLE
            authViewModel.handlerLogout()
        }

    }

    private fun setupToolbar() {
        supportActionBar?.setTitleAndBackButton("Configurações")
    }

    private fun subscribeUI() {

        with(authViewModel){

            onLogoutSucess.observe(this@SettingsActivity, Observer {
                startActivity<AuthActivity>()
                finish()
                progressSettings.visibility = GONE
            })

            onError.observe(this@SettingsActivity, Observer { error ->
                error?.let {
                    //progressAuth.visibility = GONE
                    Snackbar.make(settingsLayout, error, Snackbar.LENGTH_LONG).showError()
                    progressSettings.visibility = GONE
                }
            })

            getLoggedUser().observe(this@SettingsActivity, Observer { user ->
                user?.let {
                    name.text = it.name
                    username.text = it.username
                    Picasso.get().load(it.avatarUrl).transform(CropCircleTransformation()).into(userPhoto)
                    progressSettings.visibility = GONE
                }
            })

        }

    }

}
