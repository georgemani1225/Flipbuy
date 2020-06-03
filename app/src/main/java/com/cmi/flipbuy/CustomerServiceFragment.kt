package com.cmi.flipbuy

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.fragment_account.view.*
import kotlinx.android.synthetic.main.fragment_customer_service.*
import kotlinx.android.synthetic.main.fragment_customer_service.view.*
import kotlinx.android.synthetic.main.fragment_customer_service.view.btnCall as btnCall1

/**
 * A simple [Fragment] subclass.
 */
class CustomerServiceFragment : Fragment() {
    val phoneNumber = "0011445588"
    val REQUEST_PHONE_CALL= 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {

       val view:View= inflater.inflate(R.layout.fragment_customer_service, container, false)
        view.btnCall1.setOnClickListener {
            if (context?.let { it1 ->
                    ActivityCompat.checkSelfPermission(
                        it1,
                        Manifest.permission.CALL_PHONE
                    )
                } != PackageManager.PERMISSION_GRANTED) {
                activity?.let { it1 ->
                    ActivityCompat.requestPermissions(
                        it1,
                        arrayOf(Manifest.permission.CALL_PHONE),
                        REQUEST_PHONE_CALL
                    )
                }
            } else {
                startCall()
            }
        }




        return view

    }
    private fun startCall(){
        val callIntent= Intent(Intent.ACTION_CALL)
        callIntent.data= Uri.parse("tel:"+phoneNumber)
        startActivity(callIntent)

    }


    override fun onRequestPermissionsResult(requestCode: Int,permissions: Array<out String>,grantResults: IntArray){
        if (requestCode==REQUEST_PHONE_CALL)startCall()
    }
}







