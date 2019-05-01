package br.com.flanelinha.app.common.repository

import br.com.flanelinha.app.common.constants.REAL_TIME_DATABASE_USERS
import br.com.flanelinha.app.login.data.model.UserAuthModel
import br.com.flanelinha.app.login.data.model.UserModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase

class FirebaseRepository {

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firebaseDatabase: FirebaseDatabase = FirebaseDatabase.getInstance()

    fun sigInWithEmailAndPassword(userAuth: UserAuthModel): Task<AuthResult> {
        return firebaseAuth.signInWithEmailAndPassword(userAuth.email, userAuth.password)
    }

    fun getCurrentUser(): FirebaseUser?{
        return firebaseAuth.currentUser
    }

    fun createUserWithEmailAndPassword(userAuth: UserAuthModel): Task<AuthResult>{
        return firebaseAuth.createUserWithEmailAndPassword(userAuth.email, userAuth.password)
    }

    fun saveAtRealTimeDatabase(user: UserModel): Task<Void>{
        return firebaseDatabase.getReference(REAL_TIME_DATABASE_USERS)
                .child(FirebaseAuth.getInstance().currentUser!!.uid)
                .setValue(user)
    }
}