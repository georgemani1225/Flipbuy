package com.cmi.flipbuy.activity

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.cmi.flipbuy.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.IOException
import java.util.*
import com.google.firebase.database.DatabaseReference
import kotlin.collections.HashMap


class AdminAddProductActivity : AppCompatActivity() {

    lateinit var categoryName: String
    lateinit var AddNewProduct: Button
    lateinit var InputProductName: EditText
    lateinit var InputProductDescription: EditText
    lateinit var InputProductPrice: EditText
    lateinit var ImageProduct: ImageView
    lateinit var Description: String
    lateinit var ProductName: String
    lateinit var ProductPrice: String
    private lateinit var productsRef: DatabaseReference
    private var filePath: Uri? = null
    internal var storage: FirebaseStorage? = null
    internal var storageReference: StorageReference? = null
    private val PICK_IMAGE_REQUEST = 1234

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.data != null) {
            filePath = data.data
            try {
                val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, filePath)
                ImageProduct.setImageBitmap(bitmap)
            } catch (e: IOException) {
                e.printStackTrace()
                }
        }
    }

    private fun uploadFile() {
        if (filePath != null) {
            val imageRef = storageReference!!.child("ProductImages/" + UUID.randomUUID().toString())
            imageRef.putFile(filePath!!)
                .addOnSuccessListener {
                    Toast.makeText(this, "Product added Successfully", Toast.LENGTH_LONG).show()

                    imageRef.downloadUrl.addOnSuccessListener {

                        Log.d("AdminAddProductActivity","File Location:$it")
                        saveUserToFirebaseDatabase(it.toString())
                    finish()

                    }
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Failed to upload image", Toast.LENGTH_LONG).show()
                }
        }
    }

    private fun showFileChooser() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE_REQUEST)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_add_product)
        val intent = intent
        categoryName = (if (intent != null) intent.getStringExtra("category") else null).toString()
        productsRef = FirebaseDatabase.getInstance().getReference().child("Products")
        AddNewProduct = findViewById(R.id.btnAddProduct)
        InputProductName = findViewById(R.id.etProductName)
        InputProductDescription = findViewById(R.id.etProductDescription)
        InputProductPrice = findViewById(R.id.etPrice)
        ImageProduct = findViewById(R.id.imgProduct)
        storage = FirebaseStorage.getInstance()
        storageReference = storage!!.reference

        ImageProduct.setOnClickListener {
            showFileChooser()
        }

        AddNewProduct.setOnClickListener {
            Description = InputProductDescription.text.toString()
            ProductPrice = InputProductPrice.text.toString()
            ProductName = InputProductName.text.toString()
            if (Description.isEmpty()) {
                Toast.makeText(this, "Product description is mandatory", Toast.LENGTH_LONG).show()
            } else if (ProductName.isEmpty()) {
                Toast.makeText(this, "Product name is mandatory", Toast.LENGTH_LONG).show()
            } else if (ProductPrice.isEmpty()) {
                Toast.makeText(this, "Product price is mandatory", Toast.LENGTH_LONG).show()
            } else {
                uploadFile()
            }
        }
    }

    private fun saveUserToFirebaseDatabase(productImage: String){
        val uid=FirebaseAuth.getInstance().uid?:""
        val ref=FirebaseDatabase.getInstance().getReference("/ProductInfo/$uid")
        val product=ProductInfo(uid,ProductName,productImage,Description,ProductPrice)
        ref.setValue(product)
            .addOnSuccessListener {
                Log.d("AdminAddProductActivity","Yahoooooooooooo")
            }
    }

}
class ProductInfo(val uid:String,val Name:String,val productImage:String,val Descrip:String,val Price:String){

}