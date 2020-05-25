package com.cmi.flipbuy.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.cmi.flipbuy.R
import kotlinx.android.synthetic.main.activity_admin.*
import kotlinx.android.synthetic.main.activity_register.*

class AdminActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)
        btnLoginasAdmin.setOnClickListener {
            performLoginasAdmin()
        }
        toolbar.setTitle("Admin Login")
        setSupportActionBar(toolbar)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun performLoginasAdmin() {
        val adminmail = etLoginAdminMailid.text.toString()
        val adminpassword = etLoginAdminPassword.text.toString()
        if (adminmail.isEmpty() || adminpassword.isEmpty()) {
            Toast.makeText(this, "Please enter the credentials", Toast.LENGTH_LONG).show()
            return
        } else if (!Patterns.EMAIL_ADDRESS.matcher(adminmail).matches()) {
            Toast.makeText(this, "Invalid Email Address", Toast.LENGTH_LONG).show()
            return
        }

    }
}


