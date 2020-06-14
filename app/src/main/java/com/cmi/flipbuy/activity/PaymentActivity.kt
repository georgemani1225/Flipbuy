package com.cmi.flipbuy.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.Activity
import android.content.Intent
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.razorpay.Checkout
import com.razorpay.PaymentResultListener
import org.json.JSONObject

class PaymentActivity : AppCompatActivity(), PaymentResultListener {

    lateinit var mDatabase: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Checkout.preload(applicationContext)
        setUpPayment()
    }

    private fun setUpPayment() {

        mDatabase = FirebaseDatabase.getInstance().reference.child("Users")
            .child(FirebaseAuth.getInstance().currentUser!!.uid)
        mDatabase.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val emailResultnew = dataSnapshot.child("Email").getValue().toString()
                val mobileResultnew = dataSnapshot.child("Mobile Number").getValue().toString()
                val totalpResult = dataSnapshot.child("subtotal").getValue().toString()
                val pResult = totalpResult.toInt()

                val activity: Activity = this@PaymentActivity
                val co = Checkout()

                try {
                    val options = JSONObject()
                    options.put("name", "FlipBuy")
                    options.put("description", "")
                    //You can omit the image option to fetch the image from dashboard
                    options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png")
                    options.put("currency", "INR")
                    options.put("amount", pResult * 100)

                    val prefill = JSONObject()
                    prefill.put("email", emailResultnew)
                    prefill.put("contact", mobileResultnew)

                    options.put("prefill", prefill)
                    co.open(activity, options)
                } catch (e: Exception) {
                    Toast.makeText(activity, "Error in payment: " + e.message, Toast.LENGTH_LONG)
                        .show()
                    e.printStackTrace()
                }
            }
        })

    }

    override fun onPaymentError(p0: Int, p1: String?) {
        Toast.makeText(this, "Error in payment", Toast.LENGTH_LONG).show()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onPaymentSuccess(p0: String?) {
        Toast.makeText(this, "Payment Successful", Toast.LENGTH_LONG).show()
    }
}


