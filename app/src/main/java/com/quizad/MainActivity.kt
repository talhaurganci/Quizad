package com.quizad

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.quizad.Connection.connection

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buton = findViewById<Button>(R.id.button)
        val buton2 = findViewById<Button>(R.id.button2)

        buton.setOnClickListener {
            Toast.makeText(this@MainActivity, "You clicked me.", Toast.LENGTH_SHORT).show()
            connection()
            showUsers()
        }

        buton2.setOnClickListener {
            Intent(this, Login::class.java).apply {
                startActivity(this)
            }
        }
    }
}