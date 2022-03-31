package com.quizad

import android.os.StrictMode
import java.sql.DriverManager
import java.sql.SQLException

data class User(val id: Int, val name: String)

fun showUsers() {
    val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
    StrictMode.setThreadPolicy(policy)
    try {
        val jdbcUrl = "jdbc:mariadb://server.pinet.com.tr:3306/quizad_com"
        val connection = DriverManager.getConnection(jdbcUrl, "quizad_com", "4)rY@8)5QXSAHpjH")

        println(connection.isValid(0))

        val query = connection.prepareStatement("SELECT * FROM Users")

        val result = query.executeQuery()

        val users = mutableListOf<User>()

        while (result.next()) {
            val id = result.getInt("id")

            val name = result.getString("username")

            users.add(User(id ,name))
        }
        println(users)
    }
    catch (e: SQLException){
        e.printStackTrace()
    }
}
