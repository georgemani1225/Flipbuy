package com.cmi.flipbuy.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import com.cmi.flipbuy.R
import com.cmi.flipbuy.activity.ProductDetailsActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_drawer_header.*
import kotlinx.android.synthetic.main.activity_product_details.*


class DashboardFragment : Fragment() {

    lateinit var cardShirt1: CardView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)

        cardShirt1 = view.findViewById(R.id.cardShirt1)
        cardShirt1.setOnClickListener {
            val intent = Intent(activity, ProductDetailsActivity::class.java)
            startActivity(intent)
        }

        return view
    }
}

