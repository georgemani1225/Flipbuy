package com.cmi.flipbuy.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.cmi.flipbuy.R
import com.cmi.flipbuy.fragment.DashboardFragment
import com.google.firebase.database.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_product_details.*


class ProductDetailsActivity : AppCompatActivity() {

    lateinit var cardShirt1: CardView
    lateinit var mDatabase: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)
        setSupportActionBar(toolbar)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        cardShirt1 = findViewById(R.id.cardShirt1)
        cardShirt1.setOnClickListener {
            mDatabase = FirebaseDatabase.getInstance().getReference("Products")
                .child("shirt_1")
            mDatabase.addValueEventListener(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {

                }

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val Nresult = dataSnapshot.child("ProductName").getValue().toString()
                    val Iresult = dataSnapshot.child("ProductImg").getValue().toString()
                    val DResult = dataSnapshot.child("ProductDesc").getValue().toString()
                    val Presult = dataSnapshot.child("ProductPrice").getValue().toString()
                    txtPdt_Name.text = Nresult
                    txtPdt_Desc.text = DResult
                    txtPdt_Price.text = Presult
                    Picasso.get().load(Iresult).into(pdt_Image)

                }
            })
        }
    }
}
