package com.cmi.flipbuy.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.cmi.flipbuy.R
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class AddressActivity : AppCompatActivity() {
    lateinit var text_address_1: TextInputLayout
    lateinit var text_address_2: TextInputLayout
    lateinit var text_city: TextInputLayout
    lateinit var text_state: TextInputLayout
    lateinit var text_country: TextInputLayout
    lateinit var text_pin: TextInputLayout
    lateinit var btnSave: Button
    lateinit var mDatabase: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_address)

        text_address_1 = findViewById(R.id.text_address_1)
        text_address_2 = findViewById(R.id.text_address_2)
        text_city = findViewById(R.id.text_city)
        text_state = findViewById(R.id.text_state)
        text_country = findViewById(R.id.text_country)
        text_pin = findViewById(R.id.text_pin)
        btnSave = findViewById(R.id.btnSave)

        mDatabase = FirebaseDatabase.getInstance().reference.child("Users")
            .child(FirebaseAuth.getInstance().currentUser?.uid.toString())
            .child("DeliveryAddress")
        mDatabase.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val address1Result = dataSnapshot.child("AddressLine1").getValue().toString()
                val address2Result = dataSnapshot.child("AddressLine2").getValue().toString()
                val cityResult = dataSnapshot.child("City").getValue().toString()
                val stateResult = dataSnapshot.child("State").getValue().toString()
                val countryResult = dataSnapshot.child("Country").getValue().toString()
                val pinResult = dataSnapshot.child("Pin").getValue().toString()
                if (address1Result == "null") {
                    text_address_1.editText?.setText("")
                } else {
                    text_address_1.editText?.setText(address1Result)
                }
                if (address2Result == "null") {
                    text_address_2.editText?.setText("")
                } else {
                    text_address_2.editText?.setText(address2Result)
                }
                if (cityResult == "null") {
                    text_city.editText?.setText("")
                } else {
                    text_city.editText?.setText(cityResult)
                }
                if (stateResult == "null") {
                    text_state.editText?.setText("")
                } else {
                    text_state.editText?.setText(stateResult)
                }
                if (countryResult == "null") {
                    text_country.editText?.setText("")
                } else {
                    text_country.editText?.setText(countryResult)
                }
                if (address1Result == "null") {
                    text_pin.editText?.setText("")
                } else {
                    text_pin.editText?.setText(pinResult)
                }
            }
        })

        btnSave.setOnClickListener {
            val address_1 = text_address_1.editText?.text.toString()
            val address_2 = text_address_2.editText?.text.toString()
            val city = text_city.editText?.text.toString()
            val state = text_state.editText?.text.toString()
            val country = text_country.editText?.text.toString()
            val pin = text_pin.editText?.text.toString()

            mDatabase = FirebaseDatabase.getInstance().reference.child("Users")
                .child(FirebaseAuth.getInstance().currentUser?.uid.toString())
                .child("DeliveryAddress")
            mDatabase.child("AddressLine1").setValue(address_1)
            mDatabase.child("AddressLine2").setValue(address_2)
            mDatabase.child("City").setValue(city)
            mDatabase.child("State").setValue(state)
            mDatabase.child("Country").setValue(country)
            mDatabase.child("Pin").setValue(pin)

        }

    }
}
