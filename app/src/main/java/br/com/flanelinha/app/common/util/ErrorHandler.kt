package br.com.flanelinha.app.common.util

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.widget.TextView
import br.com.flanelinha.app.R
import br.com.flanelinha.app.cars.model.Car

class ErrorHandler {

    companion object {

        fun showErrorMessage(context: Context, message: String) {
            val alertDialog = AlertDialog.Builder(context).create()
            alertDialog.setTitle("Atenção")
            alertDialog.setMessage(message)
            alertDialog.setCancelable(true)

            alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface, which: Int) {
                    alertDialog.dismiss()
                }
            })
            alertDialog.show()
        }

        fun showDeleteDialog(context: Context, car: Car?, deleteAction: ()  -> Unit){
            val alertDialog = AlertDialog.Builder(context).create()
            alertDialog.setTitle("Tem Certeza que deseja deletar este veículo?")
            alertDialog.setMessage("Modelo: ${car?.model} Placa:${car?.plate}")
            alertDialog.setCancelable(true)

            alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Cancelar", object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface, which: Int) {
                    alertDialog.dismiss()
                }
            })

            alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Deletar", object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface, which: Int) {
                    deleteAction()
                    alertDialog.dismiss()
                }
            })
            alertDialog.show()
        }

        fun showInsertUpdateDialog(context: Context, car: Car?, buttonLabel: String, action: (car: Car)  -> Unit){
            val alertDialog = AlertDialog.Builder(context).create()
            var viewInflated = LayoutInflater.from(context).inflate(R.layout.insert_update_car, null, false)

            var text_view_plate = viewInflated.findViewById<TextView>(R.id.tietPlate)
            var text_view_model = viewInflated.findViewById<TextView>(R.id.tietModel)

            car.let {
                text_view_model.text = it?.model
                text_view_plate.text = it?.plate
            }

            alertDialog.setCancelable(true)
            alertDialog.setView(viewInflated)
            alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Cancelar", object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface, which: Int) {
                    alertDialog.dismiss()
                }
            })

            alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, buttonLabel, object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface, which: Int) {
                    if(car == null){
                        action(Car(0, text_view_plate.text.toString(), text_view_plate.text.toString()))
                    } else {
                        car.plate = text_view_plate.text.toString()
                        car.model = text_view_model.text.toString()
                        action(car)
                    }
                    text_view_model.text = ""
                    text_view_plate.text = ""
                    alertDialog.dismiss()
                }
            })
            alertDialog.show()
        }
    }
}