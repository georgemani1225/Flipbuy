package com.cmi.flipbuy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    val mAuth = FirebaseAuth.getInstance()
    lateinit var mDatabase: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val btnRegister = findViewById<View>(R.id.btnRegister) as Button
        mDatabase = FirebaseDatabase.getInstance().getReference("Names")

        toolbar.setTitle("Register Yourself")
        setSupportActionBar(toolbar)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        btnRegister.setOnClickListener(View.OnClickListener { view ->
            register()
        })
    }

    private fun register() {
        val etName = findViewById<View>(R.id.etName) as EditText
        val etEmail = findViewById<View>(R.id.etEmail) as EditText
        val etMobileno = findViewById<View>(R.id.etMobileno) as EditText
        val etAddress = findViewById<View>(R.id.etAddress) as EditText
        val etRegisterPassword = findViewById<View>(R.id.etRegisterPassword) as EditText
        val etConfirmPassword = findViewById<View>(R.id.etConfirmPassword) as EditText

        var name = etName.text.toString()
        var email = etEmail.text.toString()
        var mobileno = etMobileno.text.toString()
        var daddress = etAddress.text.toString()
        var rpassword = etRegisterPassword.text.toString()
        var cpassword = etConfirmPassword.text.toString()

        if (!name.isEmpty() && !email.isEmpty() && !mobileno.isEmpty() && !daddress.isEmpty() && !rpassword.isEmpty() && !cpassword.isEmpty()) {
            mAuth.createUserWithEmailAndPassword(email, rpassword)
                .addOnCompleteListener(this, OnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val user = mAuth.currentUser
                        val uid = user!!.uid
                        mDatabase.child(uid).child("Name").setValue(name)
                        mDatabase.child(uid).child("Mobile Number").setValue(mobileno)
                        mDatabase.child(uid).child("Delivery Address").setValue(daddress)
                    } else {
                        Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
                    }
                })
        } else {
            Toast.makeText(this, "Please enter the credentials", Toast.LENGTH_LONG).show()
        }


    }
}
