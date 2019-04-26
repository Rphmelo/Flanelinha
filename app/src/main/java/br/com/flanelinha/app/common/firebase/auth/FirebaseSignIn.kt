package br.com.flanelinha.app.common.firebase.auth

import br.com.flanelinha.app.login.data.model.UserAuthModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseSignIn @Inject constructor(private val firebaseAuth: FirebaseAuth){

    fun withEmailAndPassword(userAuth: UserAuthModel): Task<AuthResult> {
        return firebaseAuth.signInWithEmailAndPassword(userAuth.email, userAuth.password)
    }
}