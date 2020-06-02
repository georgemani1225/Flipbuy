package com.cmi.flipbuy.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cmi.flipbuy.R

class CartFragment : Fragment() {
    lateinit var recyclerview:RecyclerView
    lateinit var layoutManager:RecyclerView.LayoutManager
    lateinit var btnNext:Button
    lateinit var txtTotalPrice:TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_my_cart, container, false)
        /*val view: View = inflater!!.inflate(R.layout.fragment_my_cart, container, false)

        btnNext.setOnClickListener{
            Log.d("btnSetup", "Selected")
        }
        recyclerview=view.findViewById(R.id.CartList)
        layoutManager=LinearLayoutManager(activity)
        return view*/




    }
   /* companion object {
        fun onCreate(): CartFragment {
            return CartFragment()
        }
    }*/



}
