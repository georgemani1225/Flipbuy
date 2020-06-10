package com.cmi.flipbuy.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cmi.flipbuy.R
import com.cmi.flipbuy.model.Cart
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.cart_items_layout.view.*


class CartActivity : AppCompatActivity() {

    lateinit var cList: RecyclerView
    lateinit var mDatabase: DatabaseReference
    lateinit var btnCheckout: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        btnCheckout = findViewById(R.id.btnCheckout)

        cList = findViewById(R.id.cList)
        cList.setHasFixedSize(true)
        cList.setLayoutManager(LinearLayoutManager(this))
        mDatabase = FirebaseDatabase.getInstance().reference.child("Users")
            .child(FirebaseAuth.getInstance().currentUser?.uid.toString()).child("Cart")

        logRecyclerView()

        btnCheckout.setOnClickListener {
            val intent = Intent(this, PaymentActivity::class.java)
            startActivity(intent)
        }


    }

    private fun logRecyclerView() {
        var FirebaseRecyclerAdapter = object : FirebaseRecyclerAdapter<Cart, CartViewHolder>(

            Cart::class.java,
            R.layout.cart_items_layout,
            CartViewHolder::class.java,
            mDatabase

        ) {
            override fun populateViewHolder(
                viewHolder: CartViewHolder,
                model: Cart,
                position: Int
            ) {
                viewHolder.itemView.txtPname.setText(model.name)
                viewHolder.itemView.txtPprice.setText(model.price)
                Picasso.get().load(model.image).into(viewHolder.itemView.imgCartItem)
                viewHolder.itemView.btnDel.setOnClickListener {
                    mDatabase.child(model.id).removeValue()
                }


            }

        }

        cList.adapter = FirebaseRecyclerAdapter
    }


    class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}