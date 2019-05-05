package br.com.flanelinha.app.cars.ui

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import br.com.flanelinha.app.R
import br.com.flanelinha.app.cars.model.Car

class CarsListAdapter(
        private val context: Context,
        private val cars: List<Car>
) :
        RecyclerView.Adapter<CarsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarsViewHolder {
        val view = LayoutInflater.from(context)
                .inflate(R.layout.cars_row, parent, false)
        return CarsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return cars.size
    }

    override fun onBindViewHolder(holder: CarsViewHolder, position: Int) {
        holder.bindView(cars[position])
    }


}
