package com.example.pregnancyyyapp

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_registration.*

class RegistrationActivity : AppCompatActivity() {
    private var firebaseAuth: FirebaseAuth? = null
    private var databaseReference: DatabaseReference? = null

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        firebaseAuth = FirebaseAuth.getInstance()

        login.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        register.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                val textName = username!!.text.toString()
                val textEmail = email!!.text.toString()
                val textAddress = address!!.text.toString()
                val textPassword = password!!.text.toString()
                val textMobile = mobile!!.text.toString()
                if (TextUtils.isEmpty(textName) || TextUtils.isEmpty(textEmail) || TextUtils.isEmpty(textAddress) || TextUtils.isEmpty(textPassword) || TextUtils.isEmpty(textMobile)) {
                    Toast.makeText(this@RegistrationActivity, "All Fields are Required", Toast.LENGTH_SHORT).show()
                } else register(textName, textEmail, textAddress, textPassword, textMobile)
            }

            private fun register(name: String, mail: String, adrs: String, pwd: String, ph: String) {
                firebaseAuth!!.createUserWithEmailAndPassword(mail, pwd).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val hashMap = HashMap<String, String>()
                        val ruser = firebaseAuth!!.currentUser
                        val userId = ruser!!.uid

                        databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(userId)

                        hashMap["userId"] = userId
                        hashMap["name"] = name
                        hashMap["mail"] = mail
                        hashMap["adrs"] = adrs
                        hashMap["pwd"] = pwd
                        hashMap["ph"] = ph
                        hashMap["newdata"] = ""

                        databaseReference!!.setValue(hashMap).addOnCompleteListener { task ->
                            Log.e("task", task.toString())
                            if (task.isSuccessful) {
                                val `in` = Intent(this@RegistrationActivity, Home::class.java)
                                Toast.makeText(this@RegistrationActivity, "Successful", Toast.LENGTH_SHORT).show()
                                `in`.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                                startActivity(`in`)
                            } else {
                                Toast.makeText(this@RegistrationActivity, task.exception!!.message, Toast.LENGTH_SHORT).show()
                            }
                        }
                    } else {
                        Toast.makeText(this@RegistrationActivity, task.exception!!.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }
}