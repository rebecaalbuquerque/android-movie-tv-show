package com.albuquerque.tvshow.modules.viewmodel

import android.arch.lifecycle.ViewModel
import com.albuquerque.tvshow.core.livedata.SingleLiveEvent
import com.albuquerque.tvshow.modules.auth.business.AuthBusiness

class AuthViewModel : ViewModel() {

    var onLoginError = SingleLiveEvent<String>()

    fun doLogin(user: String, pass: String){

        AuthBusiness.doLogin(user, string,

                onSuccess = {

                },

                onError = {
                    onLoginError.call()
                }
        )

    }
}