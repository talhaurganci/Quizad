package com.quizad

import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import java.sql.Connection
import java.sql.DriverManager

class Login : AppCompatActivity() {
    lateinit var mGoogleSignInClient: GoogleSignInClient
    private val RC_SIGN_IN = 9001
    val jdbcUrl = "jdbc:mariadb://server.pinet.com.tr:3306/quizad_com"
    val table = "Users"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        val login_buton = findViewById<Button>(R.id.google_login_btn)
        val cikis_buton = findViewById<Button>(R.id.signout_button)

        val gso =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("109847654913-infbfa1avk267dlddustjjod1k2ul338.apps.googleusercontent.com")
                .requestEmail()
                    .requestServerAuthCode("109847654913-infbfa1avk267dlddustjjod1k2ul338.apps.googleusercontent.com")
                .build()


        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        login_buton.setOnClickListener {
            signIn()
        }
        cikis_buton.setOnClickListener {
            signOut()
        }
    }

    private fun signIn() {
        val signInIntent = mGoogleSignInClient.signInIntent
        startActivityForResult(
            signInIntent, RC_SIGN_IN
        )
    }

    private fun signOut() {
        mGoogleSignInClient.signOut()
            .addOnCompleteListener(this) {
                // Update your UI here
            }
    }

    private fun revokeAccess() {
        mGoogleSignInClient.revokeAccess()
            .addOnCompleteListener(this) {
                // Update your UI here
            }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task =
                GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
            val account = completedTask.getResult(
                ApiException::class.java
            )
            Log.i("AES", account.grantedScopes.toString())
            // Signed in successfully
            val googleId = account?.id ?: ""
            Log.i("Google ID",googleId)

            val googleFirstName = account?.givenName ?: ""
            Log.i("Google First Name", googleFirstName)

            val googleLastName = account?.familyName ?: ""
            Log.i("Google Last Name", googleLastName)

            val googleEmail = account?.email ?: ""
            Log.i("Google Email", googleEmail)

            val googleProfilePicURL = account?.photoUrl.toString()
            Log.i("Google Profile Pic URL", googleProfilePicURL)

            val googleIdToken = account?.idToken ?: ""
            Log.i("Google ID Token", googleIdToken)

            val serverAuthCode = account?.serverAuthCode ?: ""
            Log.i("Server Auth Code", serverAuthCode)


            val myIntent = Intent(this, DetailsActivity::class.java)
            myIntent.putExtra("google_id", googleId)
            myIntent.putExtra("google_first_name", googleFirstName)
            myIntent.putExtra("google_last_name", googleLastName)
            myIntent.putExtra("google_email", googleEmail)
            myIntent.putExtra("google_profile_pic_url", googleProfilePicURL)
            myIntent.putExtra("google_id_token", googleIdToken)
            myIntent.putExtra("server_auth_code", serverAuthCode)
            this.startActivity(myIntent)

            fun addUsers() {
                val jdbcUrl = "jdbc:mariadb://server.pinet.com.tr:3306/quizad_com"
                val connection = DriverManager.getConnection(jdbcUrl, "quizad_com", "4)rY@8)5QXSAHpjH")

                val sql = "INSERT INTO $table (`firstname`, `lastname`, `photourl`, `googleidtoken`, `serverauthcode`) VALUES ('$googleFirstName', '$googleLastName','$googleProfilePicURL','$googleIdToken', '$serverAuthCode')"
                with(connection) {
                    createStatement().execute(sql)
                    commit()
                }
            }

            //addUsers()
            //Kod askıya alındı.
            print(account)

        } catch (e: ApiException) {
            // Sign in was unsuccessful
            Log.e(
                "failed code=", e.statusCode.toString()
            )
        }
    }

}