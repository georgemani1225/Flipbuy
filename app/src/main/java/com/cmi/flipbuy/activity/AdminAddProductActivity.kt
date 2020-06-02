package com.cmi.flipbuy.activity

import android.app.Activity
import android.content.Intent

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.NonNull
import com.cmi.flipbuy.R
import android.provider.*
import com.google.android.gms.tasks.Task
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import java.io.IOException
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.view.View
import com.google.android.gms.tasks.Continuation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import java.util.Calendar.getInstance
import kotlin.collections.HashMap

class AdminAddProductActivity : AppCompatActivity() {

    var categoryName: String? = null
    lateinit var AddNewProduct: Button
    lateinit var InputProductName: EditText
    lateinit var InputProductDescription: EditText
    lateinit var InputProductPrice: EditText
    lateinit var ImageProduct: ImageView

    //lateinit var ImageUri: Uri
    lateinit var Description: String
    lateinit var ProductName: String
    lateinit var ProductPrice: String
    private lateinit var productsRef: DatabaseReference
    // private var firebaseStorage: FirebaseStorage? = null

    private var filePath: Uri? = null
    internal var storage: FirebaseStorage? = null
    internal var storageReference: StorageReference? = null

    //private val IMAGE_PICK_CODE=71
    //private var storageReference: StorageReference? = null
    //private var downloadImageUrl: String? = null
    lateinit var productId: String

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
                    Toast.makeText(this, "Image Uploaded Successfully", Toast.LENGTH_LONG).show()
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
        //firebaseStorage = FirebaseStorage.getInstance()
        //storageReference = FirebaseStorage.getInstance().reference
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
                val refInfo = FirebaseDatabase.getInstance().getReference("ProductInfo")
                productId = refInfo.push().key.toString()
                val productInformation =
                    ProductInfo(productId, ProductName, Description, ProductPrice)
                refInfo.child(productId).setValue(productInformation).addOnCompleteListener {
                    Toast.makeText(
                        applicationContext,
                        "Product details saved successfully",
                        Toast.LENGTH_LONG
                    ).show()
                    uploadFile()
                }
            }
        }
    }


    /* private fun addUploadRecordToDb(uri: String){
         val db = FirebaseFirestore.getInstance()

         val data = HashMap<String, Any>()
         data["imageUrl"] = uri

         db.collection("posts")
             .add(data)
             .addOnSuccessListener { documentReference ->
                 Toast.makeText(this, "Saved to DB", Toast.LENGTH_LONG).show()
                 val productMap=HashMap<String,Any>()
                 productMap.put("pid",productRandomKey)

                 productMap.put("description",Description)
                 productMap.put("name",ProductName)
                 productMap.put("price",ProductPrice)
                 productMap.put("category",categoryName.toString())
                 productsRef.child(productRandomKey).updateChildren(productMap).addOnCompleteListener { task ->
                     if(task.isSuccessful){
                         downloadImageUrl=task.getResult().toString()
                         Toast.makeText(this, "Product uploaded successfully", Toast.LENGTH_LONG).show()
                     }
                     else{
                         val message:String=task.exception?.toString()!!
                         Toast.makeText(this, "Error:"+message, Toast.LENGTH_LONG).show()

                     }
                 }



             }
             .addOnFailureListener { e ->
                 Toast.makeText(this, "Error saving to DB", Toast.LENGTH_LONG).show()
             }
     }*/


    /*private fun uploadImage(){
       if(selectedPhotoUri==null)return
       val productRandomKey=UUID.randomUUID().toString()
       val ref=FirebaseStorage.getInstance().getReference("ProductImages")

       ref.putFile(selectedPhotoUri!!).addOnSuccessListener {
           Log.d("AdminAddProductActivity","Successfully uploaded image:${it.metadata?.path}")
       }*/

    /*val uploadTask=ref?.putFile(photoUrl!!)
    val urlTask = uploadTask?.continueWithTask(Continuation<UploadTask.TaskSnapshot, Task<Uri>> { task ->
        if (!task.isSuccessful) {
            task.exception?.let {
                throw it
            }
        }
        downloadImageUrl= storageReference?.downloadUrl.toString()
        return@Continuation storageReference?.downloadUrl


    })?.addOnCompleteListener { task ->
        if (task.isSuccessful) {
            val downloadUri = task.result
            addUploadRecordToDb(downloadUri.toString())
        } else {
            Toast.makeText(this, "Some error occurred", Toast.LENGTH_SHORT).show()
                }
    }?.addOnFailureListener{
        Toast.makeText(this, "Some error occurred", Toast.LENGTH_SHORT).show()

    }*/
}









