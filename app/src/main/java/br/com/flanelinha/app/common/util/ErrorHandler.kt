package br.com.flanelinha.app.common.util

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
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
    }
}