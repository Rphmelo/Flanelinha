package br.com.flanelinha.app.contact.ui


import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.flanelinha.app.R
import br.com.flanelinha.app.common.util.DialogUtil
import kotlinx.android.synthetic.main.fragment_contact.*

class ContactFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_contact, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (ActivityCompat.checkSelfPermission(context!!,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity as Activity,
                    arrayOf(android.Manifest.permission.CALL_PHONE),3)

        }

        btnMakeCall.setOnClickListener({
            makeCall()
        })
    }

    private fun makeCall(){

        if (ActivityCompat.checkSelfPermission(context!!,
                Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            if(tietPhoneNumber.text.toString().isEmpty()){
                DialogUtil.showMessageDialog(context!!, "É necessário preencher o telefone")
                return
            }
            val uri: String = "tel:" + tietPhoneNumber.text.toString().trim()
            val intent = Intent(Intent.ACTION_CALL)
            intent.setData(Uri.parse(uri))
            startActivity(intent)
        }

    }
}
