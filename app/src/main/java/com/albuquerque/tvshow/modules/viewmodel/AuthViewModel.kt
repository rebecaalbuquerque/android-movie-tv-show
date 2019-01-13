package com.albuquerque.tvshow.modules.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.albuquerque.tvshow.core.livedata.SingleLiveEvent
import com.albuquerque.tvshow.core.model.ErrorMessage
import com.albuquerque.tvshow.modules.auth.business.AuthBusiness
import com.albuquerque.tvshow.modules.auth.database.AuthDatabase
import com.albuquerque.tvshow.modules.auth.model.User
import com.google.gson.Gson
import retrofit2.HttpException

class AuthViewModel : ViewModel() {

    val onLoginSucess = SingleLiveEvent<Void>()
    val onLogoutSucess = SingleLiveEvent<Void>()
    val onError = SingleLiveEvent<String>()
    val onUserLogged = SingleLiveEvent<Void>()

    private lateinit var usuario: MutableLiveData<User>

    init {
        if(AuthDatabase.getSessionId().isNotBlank())
            onUserLogged.call()
    }

    fun handlerLogin(user: String, pass: String){
        requestLogin(user, pass)
    }

    fun handlerLogout(){
        requestLogout()
    }

    fun getLoggedUser(): LiveData<User>{
        if (!::usuario.isInitialized) {
            usuario = MutableLiveData()
            usuario.value = AuthDatabase.getUser()
        }

        return usuario
    }

    private fun requestLogin(user: String, pass: String){

        AuthBusiness.doLogin(user, pass,

                onSuccess = { it ->
                    AuthDatabase.saveOrUpdate(it)
                    onLoginSucess.call()
                },

                onError = { error ->

                    when(error){
                        is HttpException -> {
                            val errorMessage = Gson().fromJson(
                                    error.response().errorBody()?.charStream(),
                                    ErrorMessage::class.java)

                            onError.value = errorMessage.status_message
                        }

                        else -> onError.value = "Erro!!!"
                    }

                    onError.call()
                }
        )

    }

    private fun requestLogout(){
        AuthBusiness.doLogout(
                {
                    AuthDatabase.clearDatabase()
                    onLogoutSucess.call()
                },
                {
                    onError.value = "Erro logout!"
                    onError.call()
                }
        )
    }
}