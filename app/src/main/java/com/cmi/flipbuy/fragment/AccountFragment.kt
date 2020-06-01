package com.cmi.flipbuy.fragment

import android.Manifest
import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.ResultReceiver
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.FragmentActivity

import com.cmi.flipbuy.R
import com.google.android.gms.common.api.ResultTransform
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.fragment_account.*
import kotlinx.android.synthetic.main.fragment_account.view.*
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import androidx.core.provider.FontsContractCompat.FontRequestCallback.RESULT_OK as RESULT_OK


class AccountFragment : Fragment() {

    val phoneNumber = "0011445588"
    val REQUEST_PHONE_CALL= 1
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater!!.inflate(R.layout.fragment_account, container, false)

        view.btnCall.setOnClickListener{
            if(getContext()?.let { it1 -> ActivityCompat.checkSelfPermission(it1,Manifest.permission.CALL_PHONE) } !=PackageManager.PERMISSION_GRANTED){
                getActivity()?.let { it1 -> ActivityCompat.requestPermissions(it1,arrayOf(Manifest.permission.CALL_PHONE),REQUEST_PHONE_CALL) }
            }else {
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

