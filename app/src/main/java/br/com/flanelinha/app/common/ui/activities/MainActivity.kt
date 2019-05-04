package br.com.flanelinha.app.common.ui.activities

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import br.com.flanelinha.app.R
import br.com.flanelinha.app.about.AboutFragment
import br.com.flanelinha.app.cars.ui.fragments.CarListFragment
import br.com.flanelinha.app.cars.ui.fragments.RegisterCarFragment
import br.com.flanelinha.app.contact.ui.ContactFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_about -> {
                openFragment(AboutFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_car_list -> {
                openFragment(CarListFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_register -> {
                openFragment(RegisterCarFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_contact -> {
                openFragment(ContactFragment())
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
