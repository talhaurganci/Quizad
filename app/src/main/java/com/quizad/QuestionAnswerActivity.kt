package com.quizad

import android.os.Bundle
import android.os.StrictMode
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.quizad.DataClasses.AnswersMapper
import com.quizad.DataClasses.Question_Options
import com.quizad.DataClasses.Questions
import com.quizad.DataClasses.Users
import org.ktorm.database.Database
import org.ktorm.dsl.from
import org.ktorm.dsl.select
import java.sql.DriverManager
import java.sql.SQLException

class QuestionAnswerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.question_answer)

        val question_text = findViewById<TextView>(R.id.question_text)
        val option1 = findViewById<Button>(R.id.option1)
        val option2 = findViewById<Button>(R.id.option2)
        val option3 = findViewById<Button>(R.id.option3)
        val option4 = findViewById<Button>(R.id.option4)

        fun test() {
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)

            val jdbcUrl = "jdbc:mariadb://server.pinet.com.tr:3306/quizad_com"
            val database = Database.connect(
                    jdbcUrl,
                    driver = "com.mysql.jdbc.Driver",
                    user = "quizad_com",
                    password = "4)rY@8)5QXSAHpjH"
            )

            for (row in database.from(Question_Options).select()) {
                val id: Int? = row[Question_Options.question_id]
                val answers: List<AnswersMapper>? = row[Question_Options.answers]
                println("$id, $answers")
            }
        }

        /*fun getQuestionText() {
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
            try {
                val jdbcUrl = "jdbc:mariadb://server.pinet.com.tr:3306/quizad_com"
                val connection = DriverManager.getConnection(jdbcUrl, "quizad_com", "4)rY@8)5QXSAHpjH")

                println(connection.isValid(0))

                val getQTextQuery = connection.prepareStatement("SELECT * FROM questions WHERE id=1")
                //val getOpTextQuery = connection.prepareCall("SELECT * FROM question_options WHERE question_id=1")
                val questions = mutableListOf<Questions>()

                val QTextResult = getQTextQuery.executeQuery()
               // val OpTextResult = getOpTextQuery.executeQuery()

               while (QTextResult.next()) {
                   val id = QTextResult.getInt("id")
                   val questiontext = QTextResult.getString("question_text")
                   val difficulty = QTextResult.getInt("difficulty")
                   questions.add(Questions(id,questiontext,difficulty))
                   question_text.setText(questiontext)
                   println(questions)
               }

               /* while (OpTextResult.next()) {
                    val optiontext = QTextResult.getString("answers")
                    option1.setText(optiontext)
                }*/
            } catch (e: SQLException) {
                e.printStackTrace()
            }
        }
*/
        /*fun getQuestionOptions() {

        }
*/
        //getQuestionText()
        test()
    }
}

