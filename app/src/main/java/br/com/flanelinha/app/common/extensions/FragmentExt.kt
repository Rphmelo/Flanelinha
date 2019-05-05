package br.com.flanelinha.app.common.extensions

import android.support.v4.app.Fragment
import br.com.flanelinha.app.R

fun Fragment.openFragment(fragment: Fragment) {
    val transaction = fragment.fragmentManager!!.beginTransaction()
    transaction.replace(R.id.container, fragment)
    transaction.addToBackStack(null)
    transaction.commit()
}