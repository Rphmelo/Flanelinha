package br.com.flanelinha.app.cars.ui.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText

import br.com.flanelinha.app.R
import br.com.flanelinha.app.cars.model.Car
import br.com.flanelinha.app.cars.ui.viewmodel.CarViewModel
import kotlinx.android.synthetic.main.fragment_register_car.*

class RegisterCarFragment : Fragment() {

    private lateinit var carViewModel: CarViewModel
    private var isUpdate = false
    private lateinit var carToUpdate: Car

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_register_car, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        carViewModel = CarViewModel(context!!)

        setUpdateScreen()

        btnRegisterCars.setOnClickListener({
            if(isUpdate){
                carViewModel.updateCar(carToUpdate)
                carViewModel.loadCars()
            } else {
                carViewModel.saveCar(Car(0, tietPlate.text.toString(), tietModel.text.toString()))
            }
        })
    }

    private fun setUpdateScreen(){
        if(arguments == null){
            return
        }

        isUpdate = arguments!!.getBoolean("isUpdate")

        if(isUpdate){
            carToUpdate = Car(
                    arguments!!.getLong("id"),
                    arguments!!.getString("plate"),
                    arguments!!.getString("model")
            )

            tietModel.setText(carToUpdate.model)
            tietPlate.setText(carToUpdate.plate)
            btnRegisterCars.setText(R.string.title_update)
        }
    }

}
