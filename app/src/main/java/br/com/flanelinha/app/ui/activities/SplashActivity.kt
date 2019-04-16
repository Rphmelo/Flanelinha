package br.com.flanelinha.app.ui.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import br.com.flanelinha.app.R
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    private lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        preferences = getSharedPreferences("userPreferences", Context.MODE_PRIVATE)
        val isFirstOpen = preferences.getBoolean("open_first", true)

        if(isFirstOpen) {
            markAppAlreadyOpen()
            load()
        } else {
            showMain()
        }
    }

    private fun markAppAlreadyOpen() {
        val editor = preferences.edit()
        editor.putBoolean("open_first", false)
        editor.apply()
    }

    private fun load() {
        val animation = AnimationUtils.loadAnimation(this, R.anim.splash)
        animation.reset()
        ivLogo.startAnimation(animation)

        Handler().postDelayed({
            showMain()
        }, 3200L)
    }

    private fun showMain() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}
