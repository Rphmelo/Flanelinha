package br.com.flanelinha.app.login.ui.viewmodel

import android.arch.lifecycle.ViewModel
import android.content.Context
import android.widget.Toast
import br.com.flanelinha.app.common.repository.FirebaseRepository

import br.com.flanelinha.app.login.data.model.UserAuthModel
import br.com.flanelinha.app.login.data.model.UserModel

class LoginViewModel(val context: Context) : ViewModel() {

    private val firebaseRepository: FirebaseRepository = FirebaseRepository()

    fun isLoggedUser(): Boolean{
        return firebaseRepository.getCurrentUser() != null
    }

    fun signInWithEmailAndPassword(userAuth: UserAuthModel, callback: () -> Unit){
        firebaseRepository.sigInWithEmailAndPassword(userAuth)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        callback()
                    } else {
                        Toast.makeText(context, it.exception?.message, Toast.LENGTH_SHORT).show()
                    }
                }
    }

    fun createUserWithEmailAndPassword(userAuth: UserAuthModel, callback: () -> Unit){
        firebaseRepository.createUserWithEmailAndPassword(userAuth)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        callback()
                    } else {

                    }
                }
    }

    fun saveAtRealTimeDatabase(user: UserModel){
        firebaseRepository.saveAtRealTimeDatabase(user)
                .addOnCompleteListener {
                    if (it.isSuccessful) {

                    } else {

                    }
                }
    }
}