package com.example.influxapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.influxapp.database.DataBaseHelper

class MainActivity : AppCompatActivity() {

    private lateinit var etUsername:EditText
    private lateinit var etPassword:EditText
    private lateinit var database:DataBaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val registerButton = findViewById<Button>(R.id.RegisterBtn)
        val loginButton = findViewById<Button>(R.id.LogBtn)
        etUsername = findViewById<EditText>(R.id.editTextTextPersonName)
        etPassword = findViewById<EditText>(R.id.editTextTextPassword)
        database = DataBaseHelper(this)

        registerButton.setOnClickListener{
            val intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }


        loginButton.setOnClickListener {
            val usernameEditText = etUsername.text.toString()
            val passwordEditText = etPassword.text.toString()
            val result = database.userValidationFunction(usernameEditText,passwordEditText)

//            Toast.makeText(this@MainActivity,"Hello!",Toast.LENGTH_SHORT).show()


            if(TextUtils.isEmpty(usernameEditText)||TextUtils.isEmpty(passwordEditText)){
                Toast.makeText(this@MainActivity,"Enter your username and password!",Toast.LENGTH_SHORT).show()
            }else {

                if (result == true){
                    Toast.makeText(this@MainActivity, "Successful!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this,AchievmentActivity::class.java)
                    startActivity(intent)
                    finish()
                }else {
                    Toast.makeText(this@MainActivity, "Something Went Wrong!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}