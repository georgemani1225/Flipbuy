package com.cmi.flipbuy.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.cmi.flipbuy.R
import com.cmi.flipbuy.activity.AdminAddProductActivity
import kotlinx.android.synthetic.main.fragment_my_cart.*
import kotlinx.android.synthetic.main.fragment_my_cart.view.*

class CartFragment : Fragment() {
    lateinit var recyclerview:RecyclerView
    lateinit var layoutManager:RecyclerView.LayoutManager
    lateinit var btnNext:Button
    lateinit var txtTotalPrice:TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {


        val view: View = inflater!!.inflate(R.layout.fragment_my_cart, container, false)

        view.btnNext.setOnClickListener{

        }
        recyclerview=view.findViewById(R.id.CartList)
        layoutManager=LinearLayoutManager(activity)
        return view




    }
    companion object {
        fun onCreate(): CartFragment {
            return CartFragment()
        }
    }



}
