package com.example.pregnancyyyapp.ui

import android.content.Intent
import android.graphics.drawable.AnimatedVectorDrawable
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.pregnancyyyapp.R


class FirstActivity : AppCompatActivity() {
    lateinit var animation: AnimatedVectorDrawable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        /*val animatedVectorDrawable = AppCompatResources.getDrawable(this, R.drawable.avd_one_two) as AnimatedVectorDrawable?
        image.setImageDrawable(animatedVectorDrawable)
        animatedVectorDrawable!!.start()*/

        Handler(mainLooper).postDelayed({
            val intent = Intent(this@FirstActivity, Home::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            finish()
        }, 4000)
    }
}
