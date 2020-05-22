package activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.cmi.flipbuy.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    lateinit var mAuth: FirebaseAuth
    lateinit var mDatabase: DatabaseReference
    var firebaseUserID: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val btnRegister = findViewById<View>(R.id.btnRegister) as Button

        toolbar.setTitle("Register Yourself")
        setSupportActionBar(toolbar)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        mAuth = FirebaseAuth.getInstance()
        btnRegister.setOnClickListener {
            register()
        }
    }

    private fun register() {
        val etName = findViewById<View>(R.id.etName) as EditText
        val etEmail = findViewById<View>(R.id.etEmail) as EditText
        val etMobileno = findViewById<View>(R.id.etMobileno) as EditText
        val etAddress = findViewById<View>(R.id.etAddress) as EditText
        val etRegisterPassword = findViewById<View>(R.id.etRegisterPassword) as EditText
        val etConfirmPassword = findViewById<View>(R.id.etConfirmPassword) as EditText

        var name = etName.text.toString()
        var email = etEmail.text.toString()
        var mobileno = etMobileno.text.toString()
        var daddress = etAddress.text.toString()
        var rpassword = etRegisterPassword.text.toString()
        var cpassword = etConfirmPassword.text.toString()

        if (name.isNotEmpty() && email.isNotEmpty() && mobileno.isNotEmpty() && daddress.isNotEmpty() && rpassword.isNotEmpty() && cpassword.isNotEmpty()) {
            mAuth.createUserWithEmailAndPassword(email, rpassword)
                .addOnCompleteListener{ task ->
                    if (task.isSuccessful) {
                       firebaseUserID = mAuth.currentUser!!.uid
                       mDatabase = FirebaseDatabase.getInstance().reference.child("Users").child(firebaseUserID)
                        val userHashMap = HashMap<String, Any>()
                        userHashMap["uid"] = firebaseUserID
                        userHashMap["Name"] = name
                        userHashMap["Email"] = email
                        userHashMap["Mobile Number"] = mobileno
                        userHashMap["Delivery Address"] = daddress
                        mDatabase.updateChildren(userHashMap).addOnCompleteListener {task ->
                            if (task.isSuccessful)
                            {
                                val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                                startActivity(intent)
                                finish()
                            }
                        }

                    } else {
                        Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
                    }
                }
        } else {
            Toast.makeText(this, "Please enter the credentials", Toast.LENGTH_LONG).show()
        }



    }
}
