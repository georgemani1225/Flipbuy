package com.cmi.flipbuy.fragment

import android.Manifest
import android.app.Activity
import android.app.Activity.RESULT_OK
import android.app.ProgressDialog
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.ResultReceiver
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.FragmentActivity

import com.cmi.flipbuy.R
import com.google.android.gms.common.api.ResultTransform
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_drawer_header.*
import kotlinx.android.synthetic.main.fragment_account.*
import kotlinx.android.synthetic.main.fragment_account.view.*
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.util.*
import androidx.core.provider.FontsContractCompat.FontRequestCallback.RESULT_OK as RESULT_OK


class AccountFragment : Fragment() {

    lateinit var mDatabase: DatabaseReference
    lateinit var UserNameProfile: TextView
    lateinit var txtPhoneNumber: TextView
    lateinit var txtDelAddress: TextView
    lateinit var imgProfileFrag: de.hdodenhof.circleimageview.CircleImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_account, container, false)

        UserNameProfile = view.findViewById(R.id.UserNameProfile)
        txtPhoneNumber = view.findViewById(R.id.txtPhoneNumber)
        txtDelAddress = view.findViewById(R.id.txtDelAddress)
        imgProfileFrag = view.findViewById(R.id.imgProfileFrag)

        mDatabase = FirebaseDatabase.getInstance().getReference("Users")
            .child(FirebaseAuth.getInstance().currentUser!!.uid)
        mDatabase.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val nameResult = dataSnapshot.child("Name").getValue().toString()
                val mobileResult = dataSnapshot.child("Mobile Number").getValue().toString()
                val dResult = dataSnapshot.child("Delivery Address").getValue().toString()
                UserNameProfile.text = nameResult
                txtPhoneNumber.text = "+91" + " " + mobileResult
                txtDelAddress.text = dResult
            }
        })
        return view
    }
}

