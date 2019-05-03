package br.com.flanelinha.app.registration.ui.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import br.com.flanelinha.app.R
import br.com.flanelinha.app.login.data.model.UserAuthModel
import br.com.flanelinha.app.login.data.model.UserModel
import br.com.flanelinha.app.login.ui.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        loginViewModel = LoginViewModel(this)

        btnRegister.setOnClickListener(View.OnClickListener {
            createUserWithEmailAndPassword()
        })

    }

    fun createUserWithEmailAndPassword(){
        loginViewModel.createUserWithEmailAndPassword(
                UserAuthModel(tietPasswordSignUp.text.toString(), tietUserSignUp.text.toString()),
                UserModel(tietNameSignUp.text.toString(), tietUserSignUp.text.toString())
        )
    }



}
