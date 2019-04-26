package br.com.flanelinha.app.login.ui.activities

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import br.com.flanelinha.app.R
import br.com.flanelinha.app.common.ui.activities.BaseActivity
import br.com.flanelinha.app.login.data.model.UserAuthModel
import br.com.flanelinha.app.login.ui.viewmodel.LoginViewModel
import br.com.flanelinha.app.registration.ui.activities.ParkRegistrationActivity
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class LoginActivity : BaseActivity() {

    lateinit var loginViewModel: LoginViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun initToolbar() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        signInWithEmailAndPassword()
    }

    fun signInWithEmailAndPassword(){
        btnLogin.setOnClickListener({
            tietPassword
            loginViewModel.signInWithEmailAndPassword(
                    UserAuthModel(tietPassword.text.toString(), tietUser.text.toString()),
                    {
                        showParkRegistrationScreen()
                    }
            )
        })
    }

    private fun showParkRegistrationScreen() {
        startActivity(Intent(this, ParkRegistrationActivity::class.java))
        finish()
    }

    private fun configureViewModel() {
        loginViewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel::class.java)
    }
}
