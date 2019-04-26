package br.com.flanelinha.app.login.ui.viewmodel

import android.arch.lifecycle.ViewModel

import br.com.flanelinha.app.common.firebase.auth.FirebaseSignIn
import br.com.flanelinha.app.login.data.model.UserAuthModel
import javax.inject.Inject

class LoginViewModel @Inject constructor(var firebaseAuth: FirebaseSignIn) : ViewModel() {

    fun signInWithEmailAndPassword(userAuth: UserAuthModel, callback: () -> Unit){
        firebaseAuth.withEmailAndPassword(userAuth)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        callback()
                    } else {

                    }
                };
    }
}