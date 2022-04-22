package com.quizad

import android.os.StrictMode
import java.sql.DriverManager
import java.sql.SQLException
import com.quizad.Connection.connection

data class User(
        val id: Int,
        val firstname: String,
        val lastname: String,
        val photourl: String,
        val googleidtoken: String,
        val serverauthcode: String
        )
