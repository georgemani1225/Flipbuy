package com.cmi.flipbuy.activity

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton
import com.cmi.flipbuy.R
import com.google.android.material.floatingactionbutton.FloatingActionButton


class ProductDetailsActivity : AppCompatActivity() {
    lateinit var imageDetails:ImageView
    lateinit var txtProduct_name:TextView
    lateinit var txtProduct_Descrip:TextView
    lateinit var txtProduct_price:TextView
    lateinit var btnItemsNum:ElegantNumberButton
    lateinit var addToCart:FloatingActionButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)
        txtProduct_name=findViewById(R.id.txtPdt_details)
        txtProduct_Descrip=findViewById(R.id.txtPdt_descrip)
        txtProduct_price=findViewById(R.id.txtPdt_price)
        btnItemsNum=findViewById(R.id.num_btn)
        imageDetails=findViewById(R.id.pdt_image_details)
        addToCart=findViewById(R.id.add_product_to_cart)




    }
}
