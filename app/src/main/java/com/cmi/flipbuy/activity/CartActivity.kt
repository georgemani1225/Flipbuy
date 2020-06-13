package com.cmi.flipbuy.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cmi.flipbuy.R
import com.cmi.flipbuy.model.Cart
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_cart.*
import kotlinx.android.synthetic.main.cart_items_layout.view.*


class CartActivity : AppCompatActivity() {

    lateinit var cList: RecyclerView
    lateinit var mDatabase: DatabaseReference
    lateinit var btnCheckout: Button
    lateinit var txtTotal: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        btnCheckout = findViewById(R.id.btnCheckout)
        txtTotal = findViewById(R.id.txtTotal)

        cList = findViewById(R.id.cList)
        cList.setHasFixedSize(true)
        cList.setLayoutManager(LinearLayoutManager(this))
        mDatabase = FirebaseDatabase.getInstance().reference.child("Users")
            .child(FirebaseAuth.getInstance().currentUser?.uid.toString()).child("Cart")

        mDatabase.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {

                var add = 0
                for (ds in dataSnapshot.children) {
                    val p = ds.child("price").getValue().toString()
                    val pValue = p.toInt()
                    add += pValue
                    txtTotal.text = "Sub Total: ₹ " + add.toString()
                }
                txtTotal.text = "Sub Total: ₹ " + add.toString()
            }
        })


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
                viewHolder.itemView.txtPprice.setText("₹" + model.price)
                viewHolder.itemView.txtPsize.setText("Size: " + model.size)
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
