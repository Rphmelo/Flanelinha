package br.com.flanelinha.app.login.ui.activities

import android.os.Bundle
import br.com.flanelinha.app.R
import br.com.flanelinha.app.common.ui.activities.BaseActivity

class LoginActivity : BaseActivity() {

    override fun initToolbar() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }
}
