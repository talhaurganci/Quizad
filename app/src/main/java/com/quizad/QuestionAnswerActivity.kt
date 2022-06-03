package com.quizad

import android.content.Intent
import android.os.Bundle
import android.os.StrictMode
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.quizad.DataClasses.Question_Options
import com.quizad.DataClasses.Questions
import org.ktorm.database.Database
import org.ktorm.dsl.*

class QuestionAnswerActivity : AppCompatActivity() {

    var difficulty = 1
    var questionCount = 0
    var questionsIdList: MutableList<Int> = arrayListOf()
    var random_id: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.question_answer)

        val question_text = findViewById<TextView>(R.id.question_text)
        val option1 = findViewById<Button>(R.id.option1)
        val option2 = findViewById<Button>(R.id.option2)
        val option3 = findViewById<Button>(R.id.option3)
        val option4 = findViewById<Button>(R.id.option4)
        val questionNumberText = findViewById<TextView>(R.id.question_number)

        fun getQuestionsRandomId() {
            val soru_numarasi = questionCount+1
            questionNumberText.setText("Soru "+soru_numarasi)
            if (questionCount == 2) {
                difficulty = 2
            }
            if (questionCount == 4) {
                difficulty = 3
            }
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)

            val jdbcUrl = "jdbc:mariadb://server.pinet.com.tr:3306/quizad_com"
            val database = Database.connect(
                    jdbcUrl,
                    driver = "com.mysql.jdbc.Driver",
                    user = "quizad_com",
                    password = "4)rY@8)5QXSAHpjH"
            )

            for (row in database.from(Questions).select(Questions.id).where { (Questions.difficulty eq difficulty) and (Questions.id notEq random_id) }) {
                println(random_id)
                val questionid: Int? = row[Questions.id]

                if (questionid != null) {
                    questionsIdList.add(questionid)

                }


                random_id = questionsIdList.random()
            }
            questionCount++
            if (questionCount==7) {
                Toast.makeText(this@QuestionAnswerActivity, "Tebrikler!", Toast.LENGTH_SHORT).show()
                Intent(this, MainActivity::class.java).apply {
                    startActivity(this)
                }
            }
        }

        fun getQuestionsAndAnswers() {
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)

            val jdbcUrl = "jdbc:mariadb://server.pinet.com.tr:3306/quizad_com"
            val database = Database.connect(
                    jdbcUrl,
                    driver = "com.mysql.jdbc.Driver",
                    user = "quizad_com",
                    password = "4)rY@8)5QXSAHpjH"
            )

            for (row in database.from(Questions).select().where { (Questions.id eq random_id) }) {
                val questionid: Int? = row[Questions.id]
                val questionText = row[Questions.question_text]
                val difficulty = row[Questions.difficulty]

                question_text.setText(questionText)
                for (row in database.from(Question_Options).select().where { Question_Options.question_id eq random_id }) {
                    val id: Int? = row[Question_Options.question_id]
                    val answers = row[Question_Options.answers] ?: emptyList()



                    println(answers)
/*
                    option1.setText(answers?.get(0)?.toString())
                    option2.setText(answers?.get(1)?.toString())
                    option3.setText(answers?.get(2)?.toString())
                    option4.setText(answers?.get(3)?.toString())
                    //println(mergedList)
*/
                    option1.text = answers[0].tr
                    option2.text = answers[1].tr
                    option3.text = answers[2].tr
                    option4.text = answers[3].tr

                    option1.setOnClickListener {
                        if (answers[0].is_correct == true) {
                            getQuestionsRandomId()
                            getQuestionsAndAnswers()
                        } else {
                            Toast.makeText(this@QuestionAnswerActivity, "Yanlış Cevap Verdiniz", Toast.LENGTH_SHORT).show()
                            Intent(this, MainActivity::class.java).apply {
                                startActivity(this)
                            }
                        }

                    }

                    option2.setOnClickListener {
                        if (answers[1].is_correct == true) {
                            getQuestionsRandomId()
                            getQuestionsAndAnswers()
                        } else {
                            Toast.makeText(this@QuestionAnswerActivity, "Yanlış Cevap Verdiniz", Toast.LENGTH_SHORT).show()
                            Intent(this, MainActivity::class.java).apply {
                                startActivity(this)
                            }
                        }

                    }

                    option3.setOnClickListener {
                        if (answers[2].is_correct == true) {
                            getQuestionsRandomId()
                            getQuestionsAndAnswers()
                        } else {
                            Toast.makeText(this@QuestionAnswerActivity, "Yanlış Cevap Verdiniz", Toast.LENGTH_SHORT).show()
                            Intent(this, MainActivity::class.java).apply {
                                startActivity(this)
                            }
                        }

                    }

                    option4.setOnClickListener {
                        if (answers[3].is_correct == true) {
                            getQuestionsRandomId()
                            getQuestionsAndAnswers()
                        } else {
                            Toast.makeText(this@QuestionAnswerActivity, "Yanlış Cevap Verdiniz", Toast.LENGTH_SHORT).show()
                            Intent(this, MainActivity::class.java).apply {
                                startActivity(this)
                            }
                        }

                    }

                }
            }
            questionsIdList.clear()
        }
            getQuestionsRandomId()
            getQuestionsAndAnswers()

        }
    }
