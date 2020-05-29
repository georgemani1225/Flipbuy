package com.cmi.flipbuy.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.cmi.flipbuy.R

class AdminAddProductActivity : AppCompatActivity() {
    var categoryName:String?=null
    lateinit var AddNewProduct:Button
    lateinit var InputProductName:EditText
    lateinit var InputProductDescription:EditText
    lateinit var InputProductPrice:EditText
    lateinit var ImageProduct:ImageView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_add_product)

        val intent = intent
        categoryName= (if (intent != null) intent.getStringExtra("category") else null).toString()
        AddNewProduct=findViewById(R.id.btnAddProduct)
        InputProductName=findViewById(R.id.etProductName)
        InputProductDescription=findViewById(R.id.etProductDescription)
        InputProductPrice=findViewById(R.id.etPrice)
        ImageProduct=findViewById(R.id.imgProduct)

        ImageProduct.setOnClickListener {
            openGallery()
        }
    }
    private fun openGallery(){
        val intent=Intent(Intent.ACTION_PICK)
        intent.type="image/*"
        startActivityForResult(intent,IMAGE_PICK_CODE)
    }
    companion object{
        private val IMAGE_PICK_CODE=1000;

    }
}
