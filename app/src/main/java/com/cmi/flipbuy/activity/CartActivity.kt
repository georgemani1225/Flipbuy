package com.cmi.flipbuy.activity

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
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.cart_items_layout.view.*


class CartActivity : AppCompatActivity() {

    lateinit var cList: RecyclerView
    lateinit var mDatabase: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        cList = findViewById(R.id.cList)
        cList.setHasFixedSize(true)
        cList.setLayoutManager(LinearLayoutManager(this))
        mDatabase = FirebaseDatabase.getInstance().reference.child("Cart")

        logRecyclerView()

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