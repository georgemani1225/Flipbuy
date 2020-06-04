package com.cmi.flipbuy.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView

import com.cmi.flipbuy.R
import com.cmi.flipbuy.adapter.ProductsAdapter
import com.cmi.flipbuy.model.Product


class DashboardFragment : Fragment() {

    private var arrayList: ArrayList<Product> ? = null
    private var gridView: GridView? = null
    private var productsAdapter: ProductsAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)


        return view

    }
}

