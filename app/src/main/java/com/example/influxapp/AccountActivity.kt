package com.example.influxapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class AccountActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)

        val backButton = findViewById<Button>(R.id.BackToSettingBtn)
        backButton.setOnClickListener {
            val intent = Intent(this,Settings::class.java)
            startActivity(intent)
            finish()
        }
    }
}