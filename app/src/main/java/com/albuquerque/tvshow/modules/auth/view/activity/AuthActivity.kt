package com.albuquerque.tvshow.modules.auth.view.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View.GONE
import android.view.View.VISIBLE
import com.albuquerque.tvshow.R
import com.albuquerque.tvshow.core.extensions.showError
import com.albuquerque.tvshow.core.view.BaseActivity
import com.albuquerque.tvshow.modules.shows.view.activity.HomeActivity
import com.albuquerque.tvshow.modules.viewmodel.AuthViewModel
import kotlinx.android.synthetic.main.activity_auth.*

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

        // TODO: não é para ficar nesta tela. está aqui apenas para teste
        /*btnLogout.setOnClickListener {
            progressAuth.visibility = VISIBLE
            authViewModel.handlerLogout()
        }*/

    }

    private fun subscribeUI(){

        with(authViewModel){

            // TODO: não é para ficar nesta tela. está aqui apenas para teste
            /*onLogoutSucess.observe(this@AuthActivity, Observer {
                progressAuth.visibility = GONE
                // Redireciona
            })*/

            onUserLogged.observe(this@AuthActivity, Observer {
                startActivity(Intent(this@AuthActivity, HomeActivity::class.java))
                finish()
            })

            onLoginSucess.observe(this@AuthActivity, Observer {
                progressAuth.visibility = GONE
                startActivity(Intent(this@AuthActivity, HomeActivity::class.java))
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
