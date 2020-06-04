package com.cmi.flipbuy.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import androidx.recyclerview.widget.RecyclerView
import com.cmi.flipbuy.R
import com.cmi.flipbuy.adapter.DashboardRecyclerAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recycler_dashboard_single_row.*

/**
 * A simple [Fragment] subclass.
 */
class DashboardFragment : Fragment() {
    lateinit var recyclerDashboard: RecyclerView
    lateinit var LayoutManager: RecyclerView.LayoutManager
    var products=ArrayList<String>()

   /*val productList = arrayListOf<products>(
        Product("Shirts", "Blue", "999/-", "4.5", "", R.id.imgProductImage),
        Product("Frocks", "Red", "500/-", "4", "", R.id.imgProductImage)
    )*/
    lateinit var recyclerAdapter: DashboardRecyclerAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)
        recyclerDashboard = view.findViewById(R.id.recyclerDashboard)
        LayoutManager = LinearLayoutManager(activity)
        recyclerAdapter = DashboardRecyclerAdapter(activity as Context,products)
        recyclerDashboard.adapter = recyclerAdapter
        recyclerDashboard.layoutManager = LayoutManager


        val ref: DatabaseReference = FirebaseDatabase.getInstance().getReference("ProductInfo")
            .child(FirebaseAuth.getInstance().currentUser!!.uid)

        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                dataSnapshot.children.forEach {
                    //val name:DatabaseReference=ref.child("name")

                    val name:String? = dataSnapshot.child("name").getValue().toString()
                    txtNameOfProduct.text = name
                    val PdtName:String=txtNameOfProduct.toString()
                    val descrip:String?=dataSnapshot.child("descrip").getValue().toString()
                    txtProduct_Description.text=descrip
                    val PdtDescrip:String=txtProduct_Description.toString()
                    val price:String?=dataSnapshot.child("price").getValue().toString()
                    txtPrice.text=price
                    val PdtPrice:String=txtPrice.toString()
                    val productImage:String?=dataSnapshot.child("productImage").getValue().toString()
                    Picasso.get().load(productImage).error(R.drawable.polo).into(imgProductImage)
                    //val PdtImage:String
                    products= arrayListOf<String>(
                        PdtName,PdtDescrip,PdtPrice//,productImage
                    )

                    Log.d("DashboardFragment", it.toString())

                }
            }
        })

        return view

    }
}

