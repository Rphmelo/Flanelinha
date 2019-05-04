package br.com.flanelinha.app.cars.ui.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import br.com.flanelinha.app.R
import br.com.flanelinha.app.cars.model.Car
import br.com.flanelinha.app.cars.ui.viewmodel.CarViewModel
import kotlinx.android.synthetic.main.fragment_register_car.*

class RegisterCarFragment : Fragment() {

    private lateinit var carViewModel: CarViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_register_car, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        carViewModel = CarViewModel(context!!)

        btnRegisterCars.setOnClickListener({
            carViewModel.saveCar(Car(0, tietPlate.text.toString(), tietModel.text.toString()))
        })
    }

}
