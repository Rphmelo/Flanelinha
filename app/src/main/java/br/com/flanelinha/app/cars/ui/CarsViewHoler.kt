package br.com.flanelinha.app.cars.ui

import android.support.v7.widget.RecyclerView
import android.view.View
import br.com.flanelinha.app.cars.model.Car
import kotlinx.android.synthetic.main.cars_row.view.*

class CarsViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

    fun bindView(car: Car?) = with(itemView){
        tvModel.text = car?.model
        tvPlate.text = car?.plate
    }

}