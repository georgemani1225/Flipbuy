package com.cmi.flipbuy.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.cmi.flipbuy.R
import com.cmi.flipbuy.activity.ProductDetailsActivity
import com.cmi.flipbuy.model.Product
import com.xwray.groupie.Group
import com.xwray.groupie.GroupDataObserver
import com.xwray.groupie.Item

class DashboardRecyclerAdapter(val context:Context, val itemList: ArrayList<Product>):RecyclerView.Adapter<DashboardRecyclerAdapter.DashboardViewHolder>(),
    Group {
    class DashboardViewHolder(view:View):RecyclerView.ViewHolder(view){
        val textProduct_Name:TextView=view.findViewById(R.id.txtNameOfProduct)
        val textProduct_Details:TextView=view.findViewById(R.id.txtProduct_Description)
        val texProduct_Price:TextView=view.findViewById(R.id.txtPrice)
        val imgProductImage:ImageView=view.findViewById(R.id.imgProductImage)
        val ParentLayout:LinearLayout=view.findViewById(R.id.Parentlayout)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.recycler_dashboard_single_row,parent,false)
        return DashboardViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun unregisterGroupDataObserver(groupDataObserver: GroupDataObserver) {
        TODO("Not yet implemented")
    }

    override fun getItem(position: Int): Item<*> {
        TODO("Not yet implemented")
    }

    override fun getPosition(item: Item<*>): Int {
        TODO("Not yet implemented")
    }

    override fun registerGroupDataObserver(groupDataObserver: GroupDataObserver) {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: DashboardViewHolder, position: Int) {
        val product=itemList[position]
        holder.textProduct_Name.text=product.ProductName
        holder.textProduct_Details.text=product.ProductDescrip
        holder.texProduct_Price.text=product.productPrice
        holder.imgProductImage.setImageResource(product.productImage)
        holder.ParentLayout.setOnClickListener {
            val intent=Intent(context,ProductDetailsActivity::class.java)
            context.startActivity(intent)


        }



    }
}