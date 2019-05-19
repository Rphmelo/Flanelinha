package br.com.flanelinha.app.login.ui.viewmodel

import android.arch.lifecycle.ViewModel
import android.content.Context
import br.com.flanelinha.app.common.repository.FirebaseRepository
import br.com.flanelinha.app.common.util.DialogUtil
import br.com.flanelinha.app.login.data.model.UserAuthModel
import br.com.flanelinha.app.login.data.model.UserModel

class LoginViewModel(val context: Context) : ViewModel() {

    private val firebaseRepository: FirebaseRepository = FirebaseRepository()
    private var loading = DialogUtil.getLoading(context)

    fun isLoggedUser(): Boolean{
        return firebaseRepository.getCurrentUser() != null
    }

    fun signInWithEmailAndPassword(userAuth: UserAuthModel, callback: () -> Unit){
        loading.show()
        firebaseRepository.sigInWithEmailAndPassword(userAuth)
                .addOnCompleteListener {
                    loading.hide()
                    if (it.isSuccessful) {
                        callback()
                    } else {
                        showMessageDialog("Erro ao logar")
                    }
                }
    }

    fun createUserWithEmailAndPassword(userAuth: UserAuthModel, user: UserModel){
        loading.show()
        firebaseRepository.createUserWithEmailAndPassword(userAuth)
                .addOnCompleteListener {
                    loading.hide()
                    if (it.isSuccessful) {
                        showMessageDialog("Conta Criada com sucesso!")
                    } else {
                        showMessageDialog("Erro ao criar usuário")
                    }
                }
    }


    private fun showMessageDialog(message: String) {
        DialogUtil.showMessageDialog(context, message)
    }
}