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
import com.cmi.flipbuy.model.Product
import com.google.firebase.database.*
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder

/**
 * A simple [Fragment] subclass.
 */
class DashboardFragment : Fragment() {
    lateinit var recyclerDashboard:RecyclerView
    lateinit var LayoutManager:RecyclerView.LayoutManager
    val productList= arrayListOf<Product>(
        Product("Shirts","Blue","999/-","4.5","", R.drawable.polo),
        Product("Frocks","Red","500/-","4","", R.drawable.feminine))
    lateinit var recyclerAdapter: DashboardRecyclerAdapter




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_dashboard, container, false)
        recyclerDashboard= view.findViewById(R.id.recyclerDashboard)
        LayoutManager =LinearLayoutManager(activity)
        recyclerAdapter=DashboardRecyclerAdapter(activity as Context,productList)
        recyclerDashboard.adapter=recyclerAdapter
        recyclerDashboard.layoutManager=LayoutManager
        fetchProducts()


        return view

    }
    private fun fetchProducts(){
        val ref=FirebaseDatabase.getInstance().getReference("ProductInfo")
        ref.addListenerForSingleValueEvent(object :ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(p0: DataSnapshot) {
                //val adapter:GroupAdapter<ViewHolder>()

                p0.children.forEach {
                    Log.d("DashboardFragment",it.toString())
                    val Pdt=it.getValue(Product::class.java)


                }
            }
        })
    }

}
