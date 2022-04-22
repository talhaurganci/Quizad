package com.quizad

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.widget.Button
import android.widget.Toast
import com.quizad.Connection.connection
import java.sql.DriverManager
import java.sql.SQLException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buton = findViewById<Button>(R.id.button)
        val buton2 = findViewById<Button>(R.id.button2)

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

                    val firstname = result.getString("firstname")

                    val lastname = result.getString("lastname")

                    val photourl = result.getString("photourl")

                    val googleidtoken = result.getString("googleidtoken")

                    val serverauthcode = result.getString("serverauthcode")

                    users.add(User(id ,firstname, lastname, photourl, googleidtoken, serverauthcode ))
                }
                println(users)

                // val query2 = connection.prepareStatement("INSERT INTO `Users`(`firstname`, `lastname`, `photourl`, `googleidtoken`, `serverauthcode`) VALUES ('test','test','test','test','test')")
                //query2.executeQuery()
                //above code is working!!
            }
            catch (e: SQLException){
                e.printStackTrace()
            }
        }

        buton.setOnClickListener {
            Toast.makeText(this@MainActivity, "You clicked me.", Toast.LENGTH_SHORT).show()
            //connection()
            showUsers()
        }

        buton2.setOnClickListener {
            Intent(this, Login::class.java).apply {
                startActivity(this)
            }
        }
    }
}