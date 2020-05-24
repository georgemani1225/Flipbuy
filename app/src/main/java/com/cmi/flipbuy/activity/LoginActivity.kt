package activity

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.cmi.flipbuy.R
import com.cmi.flipbuy.activity.MainActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {
    lateinit var sharedPreferences: SharedPreferences
    val nameOfDashboard = "Flip Buy"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences =
            getSharedPreferences(getString(R.string.preference_file_name), Context.MODE_PRIVATE)
        setContentView(R.layout.activity_login)

        val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)
        if (isLoggedIn) {
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnLogin.setOnClickListener {
            login()
        }

        txtForgetPassword.setOnClickListener {
            forgotPassword()
        }

        btnRegisterNew.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun forgotPassword() {
        val emailfp = etLoginMailid.text.toString()
        if (emailfp.isEmpty()) {
            Toast.makeText(this, "Please enter mail id", Toast.LENGTH_LONG).show()
        } else {
            FirebaseAuth.getInstance().sendPasswordResetEmail(emailfp)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Password reset mail sent", Toast.LENGTH_LONG).show()
                    }
                }
        }

    }

    private fun login() {
        val email = etLoginMailid.text.toString()
        val password = etLoginPassword.text.toString()
        if (email == "") {
            Toast.makeText(this, "Please enter mail id", Toast.LENGTH_LONG).show()
        } else if (password == "") {
            Toast.makeText(this, "Please enter password", Toast.LENGTH_LONG).show()
        } else {
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        savePreferences(nameOfDashboard)
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

    override fun onPause() {
        super.onPause()
        finish()
    }

    fun savePreferences(title: String) {
        sharedPreferences.edit().putBoolean("isLoggedIn", true).apply()
        sharedPreferences.edit().putString("Title", title).apply()
    }
}

