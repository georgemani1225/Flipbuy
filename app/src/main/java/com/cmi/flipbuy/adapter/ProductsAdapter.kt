package com.cmi.flipbuy.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.cmi.flipbuy.R
import com.cmi.flipbuy.model.Product

class ProductsAdapter(var context: Context, var arrayList: ArrayList<Product>): BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = View.inflate(context, R.layout.card_view_item_grid, null)
        var imageProduct: ImageView = view.findViewById(R.id.imageProduct)
        var productName: TextView = view.findViewById(R.id.txtPname)
        var listItem: Product = arrayList.get(position)
        imageProduct.setImageResource(listItem.productImage!!)
        productName.text = listItem.productName

        return view
    }

    override fun getItem(position: Int): Any {
        return arrayList.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return arrayList.size
    }
}