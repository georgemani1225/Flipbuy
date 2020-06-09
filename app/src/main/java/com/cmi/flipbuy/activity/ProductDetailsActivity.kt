package com.cmi.flipbuy.activity


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.cmi.flipbuy.R
import com.google.firebase.database.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_product_details.*
import android.os.AsyncTask
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.cmi.flipbuy.model.Cart
import com.google.firebase.auth.FirebaseAuth
import java.util.*


class ProductDetailsActivity : AppCompatActivity() {

    lateinit var btnCart: Button
    lateinit var pdt_Image: ImageView
    lateinit var txtPdt_Name: TextView
    lateinit var txt_PdtDesc: TextView
    lateinit var txt_PdtPrice: TextView
    private var pdtID: String? = null
    lateinit var mDatabase: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)
        setSupportActionBar(toolbar)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        btnCart = findViewById(R.id.btnCart)
        pdt_Image = findViewById(R.id.pdt_Image)
        txtPdt_Name = findViewById(R.id.txtPdt_Name)
        txt_PdtDesc = findViewById(R.id.txtPdt_Desc)
        txt_PdtPrice = findViewById(R.id.txtPdt_Price)
        pdtID = intent.getStringExtra("pid")
        mDatabase = FirebaseDatabase.getInstance().getReference("Products").child(pdtID!!)
        mDatabase.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val Nresult = dataSnapshot.child("ProductName").getValue().toString()
                val Iresult = dataSnapshot.child("ProductImg").getValue().toString()
                val Dresult = dataSnapshot.child("ProductDesc").getValue().toString()
                val Presult = dataSnapshot.child("ProductPrice").getValue().toString()
                txtPdt_Name.text = Nresult
                txt_PdtDesc.text = Dresult
                txt_PdtPrice.text = Presult
                Picasso.get().load(Iresult).into(pdt_Image)
            }
        })

        btnCart.setOnClickListener {
            mDatabase = FirebaseDatabase.getInstance().getReference("Products").child(pdtID!!)
            mDatabase.addValueEventListener(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {

                }

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val Nresult = dataSnapshot.child("ProductName").getValue().toString()
                    val Iresult = dataSnapshot.child("ProductImg").getValue().toString()
                    val Presult = dataSnapshot.child("ProductPrice").getValue().toString()
                    val database = FirebaseDatabase.getInstance()
                    val ref = database.reference.child("Cart")
                    val newpostRef = ref.push()
                    val pid = newpostRef.key.toString()
                    newpostRef.setValue(
                        Cart(
                            Nresult,
                            Iresult,
                            Presult,
                            pid
                        )
                    )
                    Toast.makeText(applicationContext, "Added to Cart", Toast.LENGTH_LONG).show()
                }
            })


        }
    }
}