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
    lateinit var btnS: Button
    lateinit var btnM: Button
    lateinit var btnL: Button
    lateinit var btnXL: Button
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
        btnS = findViewById(R.id.btnS)
        btnM = findViewById(R.id.btnM)
        btnL = findViewById(R.id.btnL)
        btnXL = findViewById(R.id.btnXL)
        var btnS_ba: Int = 1
        var btnM_ba: Int = 1
        var btnL_ba: Int = 1
        var btnXL_ba: Int = 1
        var size: String? = null

        btnS.setBackgroundResource(R.drawable.ic_sfill)
        btnS_ba = 2
        size = "S"

        btnS.setOnClickListener {
            if (btnM_ba == 2 || btnL_ba == 2 || btnXL_ba == 2) {
                btnM.setBackgroundResource(R.drawable.ic_m)
                btnL.setBackgroundResource(R.drawable.ic_l)
                btnXL.setBackgroundResource(R.drawable.ic_xl)
                btnM_ba = 1
                btnL_ba = 1
                btnXL_ba = 1
            }
            btnS.setBackgroundResource(R.drawable.ic_sfill)
            btnS_ba = 2
            size = "S"
        }
        btnM.setOnClickListener {
            if (btnS_ba == 2 || btnL_ba == 2 || btnXL_ba == 2) {
                btnS.setBackgroundResource(R.drawable.ic_s)
                btnL.setBackgroundResource(R.drawable.ic_l)
                btnXL.setBackgroundResource(R.drawable.ic_xl)
                btnS_ba = 1
                btnL_ba = 1
                btnXL_ba = 1
            }
            btnM.setBackgroundResource(R.drawable.ic_mfill)
            btnM_ba = 2
            size = "M"
        }
        btnL.setOnClickListener {
            if (btnS_ba == 2 || btnM_ba == 2 || btnXL_ba == 2) {
                btnS.setBackgroundResource(R.drawable.ic_s)
                btnM.setBackgroundResource(R.drawable.ic_m)
                btnXL.setBackgroundResource(R.drawable.ic_xl)
                btnM_ba = 1
                btnS_ba = 1
                btnXL_ba = 1
            }
            btnL.setBackgroundResource(R.drawable.ic_lfill)
            btnL_ba = 2
            size = "L"
        }
        btnXL.setOnClickListener {
            if (btnS_ba == 2 || btnM_ba == 2 || btnL_ba == 2) {
                btnS.setBackgroundResource(R.drawable.ic_s)
                btnM.setBackgroundResource(R.drawable.ic_m)
                btnL.setBackgroundResource(R.drawable.ic_l)
                btnS_ba = 1
                btnM_ba = 1
                btnL_ba = 1
            }
            btnXL.setBackgroundResource(R.drawable.ic_xlfill)
            btnXL_ba = 2
            size = "XL"
        }

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
                txt_PdtPrice.text = "₹" + Presult
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
                    val ref = database.reference.child("Users")
                        .child(FirebaseAuth.getInstance().currentUser?.uid.toString()).child("Cart")
                    val newpostRef = ref.push()
                    val pid = newpostRef.key.toString()
                    newpostRef.setValue(
                        Cart(
                            Nresult,
                            Iresult,
                            Presult,
                            pid,
                            size
                        )
                    )
                    Toast.makeText(applicationContext, "Added to Cart", Toast.LENGTH_LONG).show()
                }
            })
        }
    }
}