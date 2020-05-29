package com.cmi.flipbuy.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.cmi.flipbuy.R

class AdminCategoryActivity : AppCompatActivity() {
    lateinit var menDress:ImageView
    lateinit var womenDress:ImageView
    lateinit var sneakers:ImageView
    lateinit var heels:ImageView
    lateinit var Bags:ImageView
    lateinit var wallets:ImageView
    lateinit var accessories:ImageView
    lateinit var watches:ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_category)

        menDress=findViewById(R.id.tShirts)
        womenDress=findViewById(R.id.frock)

        sneakers=findViewById(R.id.shoes)
        heels=findViewById(R.id.heels)

        Bags=findViewById(R.id.Bags)
        wallets=findViewById(R.id.wallets)

        accessories=findViewById(R.id.necklace)
        watches=findViewById(R.id.watch)

        menDress.setOnClickListener {
            val intent= Intent(this@AdminCategoryActivity,AdminAddProductActivity::class.java)
            intent.putExtra("category","tshirts")
            startActivity(intent)
        }
        womenDress.setOnClickListener {
            val intent= Intent(this@AdminCategoryActivity,AdminAddProductActivity::class.java)
            intent.putExtra("category","womenDress")
            startActivity(intent)
        }
        Bags.setOnClickListener {
            val intent= Intent(this@AdminCategoryActivity,AdminAddProductActivity::class.java)
            intent.putExtra("category","bags")
            startActivity(intent)
        }
        wallets.setOnClickListener {
            val intent= Intent(this@AdminCategoryActivity,AdminAddProductActivity::class.java)
            intent.putExtra("category","wallets")
            startActivity(intent)
        }
        sneakers.setOnClickListener {
            val intent= Intent(this@AdminCategoryActivity,AdminAddProductActivity::class.java)
            intent.putExtra("category","sneakers")
            startActivity(intent)
        }
        heels.setOnClickListener {
            val intent= Intent(this@AdminCategoryActivity,AdminAddProductActivity::class.java)
            intent.putExtra("category","heels")
            startActivity(intent)
        }
        watches.setOnClickListener {
            val intent= Intent(this@AdminCategoryActivity,AdminAddProductActivity::class.java)
            intent.putExtra("category","watches")
            startActivity(intent)
        }
        accessories.setOnClickListener {
            val intent= Intent(this@AdminCategoryActivity,AdminAddProductActivity::class.java)
            intent.putExtra("category","accessories")
            startActivity(intent)
        }

    }
}
