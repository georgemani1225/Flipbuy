package com.cmi.flipbuy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth
lateinit var btnSignOut: Button
class MainActivity : AppCompatActivity() {
    val mAuth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        btnSignOut.setOnClickListener {
            mAuth.signOut()
            val intent = Intent(this,LoginActivity::class.java )
            startActivity(intent)
        }
    }
}
