package com.albuquerque.movietvshow.modules.auth.view.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View.GONE
import android.view.View.VISIBLE
import com.albuquerque.movietvshow.R
import com.albuquerque.movietvshow.core.extensions.showError
import com.albuquerque.movietvshow.core.view.activity.BaseActivity
import com.albuquerque.movietvshow.modules.auth.viewmodel.AuthViewModel
import com.albuquerque.movietvshow.modules.home.view.activity.HomeActivity
import kotlinx.android.synthetic.main.activity_auth.*
import org.jetbrains.anko.startActivity

class AuthActivity : BaseActivity() {

    private lateinit var authViewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        authViewModel = ViewModelProviders.of(this).get(AuthViewModel::class.java)

        setupButtons()
        subscribeUI()
    }

    private fun setupButtons(){

        btnLogin.setOnClickListener {
            progressAuth.visibility = VISIBLE

            authViewModel.handlerLogin(
                    authUsername.text.toString(),
                    authPassword.text.toString()
            )
        }

    }

    private fun subscribeUI(){

        with(authViewModel){

            onUserLogged.observe(this@AuthActivity, Observer {
                startActivity<HomeActivity>()
                finish()
            })

            onLoginSucess.observe(this@AuthActivity, Observer {
                progressAuth.visibility = GONE
                startActivity<HomeActivity>()
                finish()
            })

            onError.observe(this@AuthActivity, Observer { error ->
                error?.let {
                    progressAuth.visibility = GONE
                    Snackbar.make(authLayout, error, Snackbar.LENGTH_LONG).showError()
                }
            })

        }

    }
}
