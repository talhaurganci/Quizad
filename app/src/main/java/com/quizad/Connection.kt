package com.quizad

import android.os.StrictMode
import java.sql.DriverManager
import java.sql.SQLException


object Connection {
    @JvmStatic
    fun connection() {
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        try {
            val jdbcUrl = "jdbc:mariadb://server.pinet.com.tr:3306/quizad_com"
            val connection = DriverManager.getConnection(jdbcUrl, "quizad_com", "4)rY@8)5QXSAHpjH")

            println(connection.isValid(0))
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }
}