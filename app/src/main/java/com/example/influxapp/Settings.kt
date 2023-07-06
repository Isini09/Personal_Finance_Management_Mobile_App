package com.example.influxapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Settings : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val accountBtn = findViewById<Button>(R.id.AccountBtn)
        val faqButton = findViewById<Button>(R.id.FaqBtn)

        accountBtn.setOnClickListener {
            val intent = Intent(this,AccountActivity::class.java)
            startActivity(intent)
            finish()
        }

        faqButton.setOnClickListener {
            val intent = Intent(this,FaqActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}