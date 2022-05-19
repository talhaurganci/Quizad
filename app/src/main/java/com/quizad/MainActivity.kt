package com.quizad

import android.content.Intent
import android.os.Bundle
import android.os.StrictMode
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.quizad.Connection.Connection
import com.quizad.DataClasses.Users
import org.ktorm.database.Database
import org.ktorm.dsl.from
import org.ktorm.dsl.select

//import org.ktorm.database.Database
//import org.ktorm.dsl.from
//import org.ktorm.dsl.select

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buton = findViewById<Button>(R.id.button)
        val buton2 = findViewById<Button>(R.id.button2)
        val buton3 = findViewById<Button>(R.id.button3)

        fun showUsers() {
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)

                val jdbcUrl = "jdbc:mariadb://server.pinet.com.tr:3306/quizad_com"
                val database = Database.connect(
                    jdbcUrl,
                    driver = "com.mysql.jdbc.Driver",
                    user = "quizad_com",
                    password = "4)rY@8)5QXSAHpjH"
                )

            for (row in database.from(Users).select()) {
               val id: Int? = row[Users.id]
                val firstname: String? = row[Users.firstname]
                val lastname: String? = row[Users.lastname]
                val photourl: String? = row[Users.photourl]

                println("$id, $firstname, $lastname, $photourl")
            }

                //val connection = DriverManager.getConnection(jdbcUrl, "quizad_com", "4)rY@8)5QXSAHpjH")

               /* val query = connection.prepareStatement("SELECT * FROM users")

                val result = query.executeQuery()

                val users = mutableListOf<Users>()

                while (result.next()) {

                    val id = result.getInt("id")

                    val firstname = result.getString("firstname")

                    val lastname = result.getString("lastname")

                    val photourl = result.getString("photourl")

                    val googleidtoken = result.getString("googleidtoken")

                    val serverauthcode = result.getString("serverauthcode")

                    users.add(Users(id, firstname, lastname, photourl, googleidtoken, serverauthcode))
                }
                println(users)

                // val query2 = connection.prepareStatement("INSERT INTO `Users`(`firstname`, `lastname`, `photourl`, `googleidtoken`, `serverauthcode`) VALUES ('test','test','test','test','test')")
                //query2.executeQuery()
                //above code is working!!
                */

        }

        fun checkLoginState() {
            val account = GoogleSignIn.getLastSignedInAccount(this)
            if (account == null) {
                Toast.makeText(this@MainActivity, "Giriş Yapılmadı", Toast.LENGTH_SHORT).show()
            }
            if (account != null) {
                Toast.makeText(this@MainActivity, "Giriş Yapıldı", Toast.LENGTH_SHORT).show()
            }
        }

        buton.setOnClickListener {
            //Toast.makeText(this@MainActivity, "You clicked me.", Toast.LENGTH_SHORT).show()
            //connection()
            //showUsers() Attention!
            Intent(this, QuestionAnswerActivity::class.java).apply {
                startActivity(this)
            }
        }
            buton2.setOnClickListener {
                Intent(this, Login::class.java).apply {
                    startActivity(this)
                }
            }
            buton3.setOnClickListener {
                checkLoginState()
            }
        showUsers()
    }
}

