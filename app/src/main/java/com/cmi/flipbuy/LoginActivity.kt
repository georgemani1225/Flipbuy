package com.cmi.flipbuy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth


lateinit var btnRegisterNew: Button


class LoginActivity : AppCompatActivity() {
    val mAuth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val btnLogin = findViewById<View>(R.id.btnLogin) as Button
        btnRegisterNew = findViewById(R.id.btnRegisterNew)
        btnRegisterNew.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
        }
        btnLogin.setOnClickListener {
            login()
        }

    }

    private fun login() {
        val etLoginMailid = findViewById<View>(R.id.etLoginMailid) as EditText
        val etLoginPassword = findViewById<View>(R.id.etLoginPassword) as EditText

        var email = etLoginMailid.text.toString()
        var password = etLoginPassword.text.toString()

        if (email.isNotEmpty() && password.isNotEmpty()) {
            mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, OnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        Toast.makeText(this, "Successfully Logged in", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
                    }
                })
        } else {
            Toast.makeText(this, "Please fill up the credentials", Toast.LENGTH_LONG).show()
        }
    }
}
