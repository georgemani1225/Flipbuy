package com.cmi.flipbuy.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cmi.flipbuy.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_edit_profile.*
import kotlinx.android.synthetic.main.fragment_account.*

class EditProfileActivity : AppCompatActivity() {

    lateinit var mDatabase: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        mDatabase = FirebaseDatabase.getInstance().getReference("Users")
            .child(FirebaseAuth.getInstance().currentUser!!.uid)
        mDatabase.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val edtmobileResult = dataSnapshot.child("MobileNumber").getValue().toString()
                dispMobile.text = "+91" +" "+ edtmobileResult

            }
        })
    }
}
