package br.com.flanelinha.app.ui.activities

import android.support.v7.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity(){

    protected abstract fun bindIds()
    protected abstract fun initToolbar()
}