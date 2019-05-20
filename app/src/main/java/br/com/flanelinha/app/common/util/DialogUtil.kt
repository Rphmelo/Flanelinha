package br.com.flanelinha.app.common.util

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.widget.TextView
import br.com.flanelinha.app.R
import br.com.flanelinha.app.cars.model.Car

class DialogUtil {

    companion object {
        lateinit var dialog: ProgressDialog

        fun getLoading(context: Context): ProgressDialog{
            dialog = ProgressDialog(context)
            dialog.setMessage(context.resources.getString(R.string.label_loading))
            dialog.setIndeterminate(false)
            dialog.setCanceledOnTouchOutside(true)
            dialog.setCancelable(true)

            return dialog
        }

        fun showMessageDialog(context: Context, message: String) {
            val alertDialog = AlertDialog.Builder(context).create()
            alertDialog.setTitle(context.resources.getString(R.string.label_attention))
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
            alertDialog.setTitle(context.resources.getString(R.string.delete_dialog_title))
            alertDialog.setMessage("${car?.model} ${car?.plate}")
            alertDialog.setCancelable(true)

            alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, context.resources.getString(R.string.cancel_label), object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface, which: Int) {
                    alertDialog.dismiss()
                }
            })

            alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, context.resources.getString(R.string.deleter_label), object : DialogInterface.OnClickListener {
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
            alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, context.resources.getString(R.string.cancel_label), object : DialogInterface.OnClickListener {
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