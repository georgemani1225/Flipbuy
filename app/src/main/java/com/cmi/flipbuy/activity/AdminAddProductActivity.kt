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
import com.google.android.gms.tasks.Continuation
import java.util.Calendar.getInstance

class AdminAddProductActivity : AppCompatActivity() {
    var categoryName:String?=null
    lateinit var AddNewProduct:Button
    lateinit var InputProductName:EditText
    lateinit var InputProductDescription:EditText
    lateinit var InputProductPrice:EditText
    lateinit var ImageProduct:ImageView
    lateinit var ImageUri:Uri
    lateinit var Description:String
    lateinit var ProductName:String
    lateinit var ProductPrice:String

    private var firebaseStorage:FirebaseStorage?=null
    private var filePath:Uri?=null
    private val IMAGE_PICK_CODE=71
    private var storageReference:StorageReference?=null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_add_product)

        val intent = intent
        categoryName= (if (intent != null) intent.getStringExtra("category") else null).toString()

        firebaseStorage= FirebaseStorage.getInstance()
        storageReference=FirebaseStorage.getInstance().reference
        AddNewProduct=findViewById(R.id.btnAddProduct)
        InputProductName=findViewById(R.id.etProductName)
        InputProductDescription=findViewById(R.id.etProductDescription)
        InputProductPrice=findViewById(R.id.etPrice)
        ImageProduct=findViewById(R.id.imgProduct)

        ImageProduct.setOnClickListener {
            openGallery()
        }
        AddNewProduct.setOnClickListener {
            uploadImage()
        }

    }


    private fun openGallery(){
        val intent=Intent(Intent.ACTION_PICK)
        intent.type="image/*"
        startActivityForResult(intent,IMAGE_PICK_CODE)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode== IMAGE_PICK_CODE&& resultCode== Activity.RESULT_OK){
            if(data==null||data.data==null){
                return
            }
            filePath=data.data
            try{
                var bitmap=MediaStore.Images.Media.getBitmap(contentResolver,filePath)
                ImageProduct.setImageBitmap(bitmap)


            }catch (e:IOException){
                e.printStackTrace()
            }
        }
    }

    private fun addUploadRecordToDb(uri: String){
        val db = FirebaseStorage.getInstance()

        val data = HashMap<String, Any>()
        data["imageUrl"] = uri

        db.collection("posts")
            .add(data)
            .addOnSuccessListener { documentReference ->
                Toast.makeText(this, "Saved to DB", Toast.LENGTH_LONG).show()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Error saving to DB", Toast.LENGTH_LONG).show()
            }
    }
    private fun uploadImage(){

        if(filePath==null){
                Toast.makeText(this,"Product image is mandatory",Toast.LENGTH_LONG).show()
        }
        else if(TextUtils.isEmpty(Description)){
                Toast.makeText(this,"Product description is mandatory",Toast.LENGTH_LONG).show()
        }
        else if(TextUtils.isEmpty(ProductName)){
                Toast.makeText(this,"Product name is mandatory",Toast.LENGTH_LONG).show()
        }
        else if(TextUtils.isEmpty(ProductPrice)){
                Toast.makeText(this,"Product price is mandatory",Toast.LENGTH_LONG).show()
        }
        else if(filePath!=null){

            val ref=storageReference?.child("ProductImages/"+UUID.randomUUID().toString())
            Description=InputProductDescription.getText().toString()
            ProductPrice=InputProductPrice.getText().toString()
            ProductName=InputProductName.getText().toString()
            val uploadTask=ref?.putFile(filePath!!)
            val urlTask = uploadTask?.continueWithTask(Continuation<UploadTask.TaskSnapshot, Task<Uri>> { task ->
                if (!task.isSuccessful) {
                        task.exception?.let {
                            throw it
                        }
                }
                    return@Continuation ref.downloadUrl
                })?.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val downloadUri = task.result
                        addUploadRecordToDb(downloadUri.toString())
                    } else {
                        Toast.makeText(this, "Some error occurred", Toast.LENGTH_SHORT).show()

                    }
                }?.addOnFailureListener{

                }
            }else{
                Toast.makeText(this, "Please Upload an Image", Toast.LENGTH_SHORT).show()
            }
        }

    }





