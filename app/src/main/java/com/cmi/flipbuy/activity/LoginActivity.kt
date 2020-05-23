package activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.cmi.flipbuy.R
import com.cmi.flipbuy.activity.MainActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnLogin.setOnClickListener {
            login()
        }


        btnRegisterNew.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun login() {
        val email = etLoginMailid.text.toString()
        val password = etLoginPassword.text.toString()
        if (email == "") {
            Toast.makeText(this, "PLease enter mail id", Toast.LENGTH_LONG).show()
        } else if (password == "") {
            Toast.makeText(this, "PLease enter password", Toast.LENGTH_LONG).show()
        } else {
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        val intent = Intent(this, MainActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                        Toast.makeText(this, "Successfully Logged In", Toast.LENGTH_LONG)
                            .show()
                        finish()
                    }
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Error message:${it.message}", Toast.LENGTH_LONG).show()
                }
        }


    }
}

