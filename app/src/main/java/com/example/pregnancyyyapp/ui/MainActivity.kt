package com.example.pregnancyyyapp.ui

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pregnancyyyapp.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var firebaseAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firebaseAuth = FirebaseAuth.getInstance()

        register.setOnClickListener {
            startActivity(Intent(this@MainActivity, RegistrationActivity::class.java))
            finish()
        }

        login.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                val textEmail = email!!.text.toString()
                val textPassword = password!!.text.toString()
                if (TextUtils.isEmpty(textEmail) || TextUtils.isEmpty(textPassword)) {
                    Toast.makeText(this@MainActivity, "All Fields Required", Toast.LENGTH_SHORT).show()
                } else {
                    login(textEmail, textPassword)
                }
            }

            private fun login(tex_email: String, tex_password: String) {
                progressBar!!.visibility = View.VISIBLE
                firebaseAuth!!.signInWithEmailAndPassword(tex_email, tex_password).addOnCompleteListener { task ->
                    progressBar!!.visibility = View.GONE
                    if (task.isSuccessful) {
                        val intent = Intent(this@MainActivity, Home::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this@MainActivity, task.exception!!.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }
}