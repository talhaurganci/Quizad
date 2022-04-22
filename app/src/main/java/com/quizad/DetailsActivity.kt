package com.quizad

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val googleId = intent.getStringExtra("google_id")
        val googleFirstName = intent.getStringExtra("google_first_name")
        val googleLastName = intent.getStringExtra("google_last_name")
        val googleEmail = intent.getStringExtra("google_email")
        val googleProfilePicURL = intent.getStringExtra("google_profile_pic_url")
        val googleAccessToken = intent.getStringExtra("google_id_token")
        val serverAuthCode = intent.getStringExtra("server_auth_code")

        val googleIdTextView = findViewById<TextView>(R.id.google_id_textview)
        val googleFirstNameTextView = findViewById<TextView>(R.id.google_first_name_textview)
        val googleLastNameTextView = findViewById<TextView>(R.id.google_last_name_textview)
        val googleEmailTextView = findViewById<TextView>(R.id.google_email_textview)
        val googleProfilePicTextView = findViewById<TextView>(R.id.google_profile_pic_textview)
        val googleIdTokenTextView = findViewById<TextView>(R.id.google_id_token_textview)
        val serverAuthCodeTextView = findViewById<TextView>(R.id.server_auth_code_textview)

        googleIdTextView.text = googleId
        googleFirstNameTextView.text = googleFirstName
        googleLastNameTextView.text = googleLastName
        googleEmailTextView.text = googleEmail
        googleProfilePicTextView.text = googleProfilePicURL
        googleIdTokenTextView.text = googleAccessToken
        serverAuthCodeTextView.text = serverAuthCode

    }
}