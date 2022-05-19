package com.quizad.Connection

import android.os.StrictMode
import org.ktorm.database.Database

fun Connection() {
    val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
    StrictMode.setThreadPolicy(policy)

    val jdbcUrl = "jdbc:mariadb://server.pinet.com.tr:3306/quizad_com"
    val database = Database.connect(
            jdbcUrl,
            driver = "com.mysql.jdbc.Driver",
            user = "quizad_com",
            password = "4)rY@8)5QXSAHpjH"
    )
}