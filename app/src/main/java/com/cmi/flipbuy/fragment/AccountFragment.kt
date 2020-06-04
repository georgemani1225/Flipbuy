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
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_drawer_header.*
import kotlinx.android.synthetic.main.fragment_account.*
import kotlinx.android.synthetic.main.fragment_account.view.*
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import androidx.core.provider.FontsContractCompat.FontRequestCallback.RESULT_OK as RESULT_OK


class AccountFragment : Fragment() {

    lateinit var mDatabase: DatabaseReference
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_account, container, false)
        mDatabase = FirebaseDatabase.getInstance().getReference("Users")
            .child(FirebaseAuth.getInstance().currentUser!!.uid)
        mDatabase.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val nameResult = dataSnapshot.child("Name").getValue().toString()
                val emailResult = dataSnapshot.child("Email").getValue().toString()
                val mobileResult = dataSnapshot.child("MobileNumber").getValue().toString()
                val dResult = dataSnapshot.child("DeliveryAddress").getValue().toString()
                UserNameProfile.text = nameResult

            }
        })
        return view
    }


}

