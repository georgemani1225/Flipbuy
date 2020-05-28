package com.cmi.flipbuy.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.cmi.flipbuy.R

class AdminCategoryActivity : AppCompatActivity() {
    lateinit var menDress:ImageView
    lateinit var womenDress:ImageView
    lateinit var shoes:ImageView
    lateinit var heels:ImageView
    lateinit var ladiesBags:ImageView
    lateinit var menBags:ImageView
    lateinit var accessories:ImageView
    lateinit var watches:ImageView
    lateinit var cutlery:ImageView
    lateinit var toys:ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_category)
        menDress.setOnClickListener {
            val intent= Intent(this@AdminCategoryActivity,AdminAddProductActivity::class.java)
            intent.putExtra("category","tshirts")
            startActivity(intent)
        }
        womenDress.setOnClickListener {
            val intent= Intent(this@AdminCategoryActivity,AdminAddProductActivity::class.java)
            intent.putExtra("category","tshirts")
            startActivity(intent)
        }
        menDress.setOnClickListener {
            val intent= Intent(this@AdminCategoryActivity,AdminAddProductActivity::class.java)
            intent.putExtra("category","tshirts")
            startActivity(intent)
        }
        menDress.setOnClickListener {
            val intent= Intent(this@AdminCategoryActivity,AdminAddProductActivity::class.java)
            intent.putExtra("category","tshirts")
            startActivity(intent)
        }
    }
}
