package com.cmi.flipbuy.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.cmi.flipbuy.R
import kotlinx.android.synthetic.main.activity_drawer_header.*

class DrawerHeader : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawer_header)
        imgProfile.setOnClickListener {
            Toast.makeText(this, "Clicked", Toast.LENGTH_LONG).show()
        }
    }
}
