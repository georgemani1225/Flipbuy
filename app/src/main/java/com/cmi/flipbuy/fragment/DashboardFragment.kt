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
import kotlinx.android.synthetic.main.fragment_dashboard.view.*


class DashboardFragment : Fragment() {

    lateinit var cShirt1: CardView
    lateinit var cShirt2: CardView
    lateinit var cShirt3: CardView
    lateinit var cShirt4: CardView
    lateinit var cShirt5: CardView
    lateinit var cShirt6: CardView
    lateinit var cFrock1: CardView
    lateinit var cFrock2: CardView
    lateinit var cFrock3: CardView
    lateinit var cFrock4: CardView
    lateinit var cFrock5: CardView
    lateinit var cFrock6: CardView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)

        cShirt1 = view.findViewById(R.id.cShirt1)
        cShirt2 = view.findViewById(R.id.cShirt2)
        cShirt3 = view.findViewById(R.id.cShirt3)
        cShirt4 = view.findViewById(R.id.cShirt4)
        cShirt5 = view.findViewById(R.id.cShirt5)
        cShirt6 = view.findViewById(R.id.cShirt6)
        cFrock1 = view.findViewById(R.id.cFrock1)
        cFrock2 = view.findViewById(R.id.cFrock2)
        cFrock3 = view.findViewById(R.id.cFrock3)
        cFrock4 = view.findViewById(R.id.cFrock4)
        cFrock5 = view.findViewById(R.id.cFrock5)
        cFrock6 = view.findViewById(R.id.cFrock6)

        cShirt1.setOnClickListener {
            val intent = Intent(activity, ProductDetailsActivity::class.java)
            intent.putExtra("pid", "shirt_1")
            startActivity(intent)
        }
        cShirt2.setOnClickListener {
            val intent = Intent(activity, ProductDetailsActivity::class.java)
            intent.putExtra("pid", "shirt_2")
            startActivity(intent)
        }
        cShirt3.setOnClickListener {
            val intent = Intent(activity, ProductDetailsActivity::class.java)
            intent.putExtra("pid", "shirt_3")
            startActivity(intent)
        }
        cShirt4.setOnClickListener {
            val intent = Intent(activity, ProductDetailsActivity::class.java)
            intent.putExtra("pid", "shirt_4")
            startActivity(intent)
        }
        cShirt5.setOnClickListener {
            val intent = Intent(activity, ProductDetailsActivity::class.java)
            intent.putExtra("pid", "shirt_5")
            startActivity(intent)
        }
        cShirt6.setOnClickListener {
            val intent = Intent(activity, ProductDetailsActivity::class.java)
            intent.putExtra("pid", "shirt_6")
            startActivity(intent)
        }
        cFrock1.setOnClickListener {
            val intent = Intent(activity, ProductDetailsActivity::class.java)
            intent.putExtra("pid", "frock_1")
            startActivity(intent)
        }
        cFrock2.setOnClickListener {
            val intent = Intent(activity, ProductDetailsActivity::class.java)
            intent.putExtra("pid", "frock_2")
            startActivity(intent)
        }
        cFrock3.setOnClickListener {
            val intent = Intent(activity, ProductDetailsActivity::class.java)
            intent.putExtra("pid", "frock_3")
            startActivity(intent)
        }
        cFrock4.setOnClickListener {
            val intent = Intent(activity, ProductDetailsActivity::class.java)
            intent.putExtra("pid", "frock_4")
            startActivity(intent)
        }
        cFrock5.setOnClickListener {
            val intent = Intent(activity, ProductDetailsActivity::class.java)
            intent.putExtra("pid", "frock_5")
            startActivity(intent)
        }
        cFrock6.setOnClickListener {
            val intent = Intent(activity, ProductDetailsActivity::class.java)
            intent.putExtra("pid", "frock_6")
            startActivity(intent)
        }
        return view
    }
}

