package br.com.flanelinha.app.login.ui.activities

import android.content.Intent
import android.os.Bundle
import br.com.flanelinha.app.R
import br.com.flanelinha.app.common.ui.activities.BaseActivity
import br.com.flanelinha.app.login.data.model.UserAuthModel
import br.com.flanelinha.app.login.ui.viewmodel.LoginViewModel
import br.com.flanelinha.app.registration.ui.activities.LoginSignUpActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {

    lateinit var loginViewModel: LoginViewModel


    override fun initToolbar() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginViewModel = LoginViewModel(this)

        authenticate()
    }

    fun authenticate(){
        if(loginViewModel.isLoggedUser()){
            showParkRegistrationScreen()
        } else{
            btnLogin.setOnClickListener({
                signInWithEmailAndPassword()
            })
        }

    }

    fun signInWithEmailAndPassword(){
        loginViewModel.signInWithEmailAndPassword(
                UserAuthModel(tietPassword.text.toString(), tietUser.text.toString()),
                {
                    showParkRegistrationScreen()
                }
        )
    }

    private fun showParkRegistrationScreen() {
        startActivity(Intent(this, LoginSignUpActivity::class.java))
        finish()
    }

}
