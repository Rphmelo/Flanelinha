package br.com.flanelinha.app.login.ui.viewmodel

import android.arch.lifecycle.ViewModel
import android.content.Context
import br.com.flanelinha.app.common.repository.FirebaseRepository
import br.com.flanelinha.app.common.util.ErrorHandler

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
                        showErrorMessage("Erro ao logar")
                    }
                }
    }

    fun createUserWithEmailAndPassword(userAuth: UserAuthModel, user: UserModel){
        firebaseRepository.createUserWithEmailAndPassword(userAuth)
                .addOnSuccessListener {
                    showErrorMessage("Conta Criada com sucesso!")
                    saveAtRealTimeDatabase(user)
                }
                .addOnFailureListener{
                    showErrorMessage("Erro ao criar usuário")
                }
    }

    private fun saveAtRealTimeDatabase(user: UserModel){
        firebaseRepository.saveAtRealTimeDatabase(user)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        showErrorMessage("Usuário Salvo com sucesso!")
                    } else {
                        showErrorMessage(it.exception?.message.toString())
                    }
                }
    }

    private fun showErrorMessage(message: String) {
        ErrorHandler().showErrorMessage(context, message)
    }
}