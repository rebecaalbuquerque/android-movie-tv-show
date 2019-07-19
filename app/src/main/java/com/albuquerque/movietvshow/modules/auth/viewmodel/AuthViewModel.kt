package com.albuquerque.movietvshow.modules.auth.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.albuquerque.movietvshow.core.livedata.SingleLiveEvent
import com.albuquerque.movietvshow.core.utils.ErrorUtils
import com.albuquerque.movietvshow.modules.auth.business.AuthBusiness
import com.albuquerque.movietvshow.modules.auth.database.AuthDatabase
import com.albuquerque.movietvshow.modules.auth.model.User

class AuthViewModel : ViewModel() {

    val onLoginSucess = SingleLiveEvent<Void>()
    val onLogoutSucess = SingleLiveEvent<Void>()
    val onError = SingleLiveEvent<String>()
    val onUserLogged = SingleLiveEvent<Void>()

    private lateinit var user: MutableLiveData<User>

    init {
        if(AuthBusiness.getSessionId().isNotBlank())
            onUserLogged.call()
    }

    fun handlerLogin(user: String, pass: String){
        requestLogin(user, pass)
    }

    fun handlerLogout(){
        requestLogout()
    }

    fun getLoggedUser(): LiveData<User>{
        if (!::user.isInitialized) {
            user = MutableLiveData()
            user.value = AuthBusiness.getUser()
        }

        return user
    }

    private fun requestLogin(user: String, pass: String){

        AuthBusiness.doLogin(user, pass,

                onSuccess = { u ->
                    AuthDatabase.saveOrUpdate(u)

                    AuthBusiness.getFavoritesFromAPI({},{
                        onError.value = ErrorUtils.geErrorMessage(it) ?: "Erro getFavorites"
                    })

                    onLoginSucess.call()
                },

                onError = { error ->
                    onError.value = ErrorUtils.geErrorMessage(error) ?: "Erro!!!"
                }
        )

    }

    private fun requestLogout(){
        AuthBusiness.doLogout(
                {
                    AuthDatabase.clearDatabase()
                    onLogoutSucess.call()
                },
                { error ->
                    onError.value = ErrorUtils.geErrorMessage(error) ?: "Erro!!!"
                }
        )
    }

}