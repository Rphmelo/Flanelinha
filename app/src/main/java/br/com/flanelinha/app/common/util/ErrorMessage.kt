package br.com.flanelinha.app.common.util

import android.content.Context
import android.widget.Toast

class ErrorMessage {

    fun showErrorMessage(context: Context, message: String) {
        Toast.makeText(context,
                message, Toast.LENGTH_LONG).show()
    }
}