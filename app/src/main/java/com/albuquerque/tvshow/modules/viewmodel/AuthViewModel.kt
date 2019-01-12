package com.albuquerque.tvshow.modules.viewmodel

import android.arch.lifecycle.ViewModel
import com.albuquerque.tvshow.core.livedata.SingleLiveEvent
import com.albuquerque.tvshow.core.model.ErrorMessage
import com.albuquerque.tvshow.modules.auth.business.AuthBusiness
import com.albuquerque.tvshow.modules.auth.database.AuthDatabase
import com.google.gson.Gson
import retrofit2.HttpException

class AuthViewModel : ViewModel() {

    val onLoginSucess = SingleLiveEvent<Void>()
    val onLogoutSucess = SingleLiveEvent<Void>()
    val onError = SingleLiveEvent<String>()

    fun handlerLogin(user: String, pass: String){
        requestLogin(user, pass)
    }

    fun handlerLogout(){
        requestLogout()
    }

    private fun requestLogin(user: String, pass: String){

        AuthBusiness.doLogin(user, pass,

                onSuccess = {
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