package com.cmi.flipbuy.activity

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cmi.flipbuy.model.Cart
import com.cmi.flipbuy.R
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.cart_items_layout.*


class CartActivity : AppCompatActivity() {

    var recyclerView: RecyclerView? = null
    var layoutManager: LinearLayoutManager? = null
    var database: FirebaseDatabase? = null
    var allFoodData: DatabaseReference? = null
    var progressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        database = FirebaseDatabase.getInstance()
        allFoodData = database!!.getReference("Cart")
        recyclerView = findViewById(R.id.cList)
        progressDialog = ProgressDialog(this)
        progressDialog?.setMessage("Please wait ...")
        progressDialog?.setCancelable(false)
        progressDialog?.show()
        recyclerView?.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        layoutManager?.reverseLayout = true
        layoutManager?.stackFromEnd = true
        recyclerView?.setLayoutManager(layoutManager)
        loadData()
    }

    private fun loadData() {
        val options: FirebaseRecyclerOptions<Cart> =
            FirebaseRecyclerOptions.Builder<Cart>().setQuery(allFoodData!!, Cart::class.java)
                .setLifecycleOwner(this).build()
        val adapter: FirebaseRecyclerAdapter<Cart, FoodViewHolder> =
            object : FirebaseRecyclerAdapter<Cart, FoodViewHolder>(options) {
                override fun onBindViewHolder(
                    viewHolder: FoodViewHolder,
                    position: Int,
                    model: Cart
                ) {
                    viewHolder.txtPName.text = model.ProductName
                    viewHolder.txtPprice.text = model.ProductPrice
                    viewHolder.imgCartItem.text = model.ProductImg
                    if (!model.ProductImg.equals("")) {
                        Picasso.get().load(model.ProductImg).placeholder(R.drawable.profile)
                            .into(imgCartItem)
                    }

                    progressDialog!!.dismiss()
                }

                override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): FoodViewHolder {
                    val view: View = LayoutInflater.from(viewGroup.context)
                        .inflate(R.layout.cart_items_layout, viewGroup, false)
                    return FoodViewHolder(view)
                }
            }
        recyclerView?.adapter = adapter

    }


    class FoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtPName: TextView
        var txtPprice: TextView
        var imgCartItem: TextView

        init {
            txtPName = itemView.findViewById(R.id.txtPname)
            txtPprice = itemView.findViewById(R.id.txtPprice)
            imgCartItem = itemView.findViewById(R.id.imgCartItem)
        }
    }

    override fun onResume() {
        super.onResume()
        val ab: ActionBar? = supportActionBar
        if (ab != null) {
            ab.title = "My Cart"
            ab.setDisplayHomeAsUpEnabled(true)
        }
    }
}

