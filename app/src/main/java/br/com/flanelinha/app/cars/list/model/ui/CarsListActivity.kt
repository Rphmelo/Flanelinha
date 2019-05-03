package br.com.flanelinha.app.cars.list.model.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import br.com.flanelinha.app.R
import br.com.flanelinha.app.cars.list.model.model.Carros
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_cars_list.*

class CarsListActivity : AppCompatActivity() {

    private var disposable : Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cars_list)

        carregarDados()
    }

    private fun exibeNaLista(cars: List<Carros>){
       /* rvCarros.adapter = CarsListAdapter(this, cars, {
            Toast.makeText(this, it.nome, Toast.LENGTH_LONG).show()
        })
        rvCarros.layoutManager = LinearLayoutManager(this)*/
    }

    private fun exibeErro(erro: String){
        Toast.makeText(this, erro, Toast.LENGTH_LONG).show()
    }

    private fun carregarDados(){

    }

    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()
    }
}
