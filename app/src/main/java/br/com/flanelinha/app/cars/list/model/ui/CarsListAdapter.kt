package br.com.flanelinha.app.cars.list.model.ui

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.flanelinha.app.R
//import br.com.flanelinha.recyclerview.R
import br.com.flanelinha.app.cars.list.model.model.Cars

class CarsListAdapter(
        private val context: Context,
        private val pokemons: List<Cars>,
        private val listener: (Cars) -> Unit
) :
        RecyclerView.Adapter<CarsListAdapter.CarrosViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarrosViewHolder {
        val view = LayoutInflater.from(context)
                .inflate(R.layout.carros_row, parent, false)
        return CarrosViewHolder(view)
    }

    override fun getItemCount(): Int {
        return pokemons.size
    }

    override fun onBindViewHolder(holder: CarrosViewHolder, position: Int) {
//        holder.bindView(pokemons[position], listener)
    }

    class CarrosViewHolder(itemView: View) :
            RecyclerView.ViewHolder(itemView) {
//
//        fun bindView(cars: Carros,
//                     listener: (Carros) -> Unit) = with(itemView) {
//            tvModelo.text = cars.nome
//            getPicassoAuth(itemView.context)
//                    .load("https://pokedexdx.herokuapp.com${cars.imagem}")
//                    .into(ivCarros)
//
//            setOnClickListener {listener(cars)}
//        }

    }
}
