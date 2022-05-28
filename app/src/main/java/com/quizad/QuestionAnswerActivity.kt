package com.quizad

import android.os.Bundle
import android.os.StrictMode
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.fasterxml.jackson.databind.ObjectMapper
import com.quizad.DataClasses.AnswersMapper
import com.quizad.DataClasses.Question_Options
import com.quizad.DataClasses.Questions
import org.ktorm.database.Database
import org.ktorm.dsl.*
import org.ktorm.jackson.KtormModule

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

        /* fun getQuestions() {
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)

            val jdbcUrl = "jdbc:mariadb://server.pinet.com.tr:3306/quizad_com"
            val database = Database.connect(
                    jdbcUrl,
                    driver = "com.mysql.jdbc.Driver",
                    user = "quizad_com",
                    password = "4)rY@8)5QXSAHpjH"
            )

            for (row in database.from(Questions).select().where { (Questions.difficulty eq 1) }) {
                val id: Int? = row[Questions.id]
                val questionText = row[Questions.question_text]
                val difficulty = row[Questions.difficulty]

                if (id != null) {
                    questionsList.add(id)
                    if (questionText != null) {
                        questionsList.add(questionText)
                        if (difficulty != null) {
                            questionsList.add(difficulty)
                        }
                    }
                }
            }
        }*/

        /*fun getQuestionOptions() {
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

                //println(row[Question_Options.question_id])

                if (id != null) {
                    questionOptionsList.add(id)
                    if (answers != null) {
                        questionOptionsList.addAll(answers)
                    }
                }
                //println(mergedList)
            }
        }*/

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

            for (row in database.from(Questions).select(Questions.id).where { (Questions.difficulty eq difficulty) }) {
                val questionid: Int? = row[Questions.id]

                if (questionid != null) {
                    questionsIdList.add(questionid)

                }


                random_id = questionsIdList.random()

                /*for (row in database.from(Question_Options).select().where { Question_Options.question_id eq 1 }) {

                    val id: Int? = row[Question_Options.question_id]
                    val answers: List<AnswersMapper>? = row[Question_Options.answers]

                    //println(row[Question_Options.question_id])

                    if (id != null) {
                        questionOptionsList.add(id)
                        if (answers != null) {
                            questionOptionsList.addAll(answers)
                        }
                    }
                    //println(mergedList)

                }*/
            }
            questionCount++
            // println(questionCount)
            //println(questionsList.random())
            //println(questionsList)
        }

        fun test2() {
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

                //println("$questionid, $questionText, $difficulty")

                for (row in database.from(Question_Options).select().where { Question_Options.question_id eq random_id }) {


                    val id: Int? = row[Question_Options.question_id]
                    //val answers: List<AnswersMapper>? = row[Question_Options.answers]
                    val answers = row[Question_Options.answers] ?: emptyList()

                    //println(row[Question_Options.question_id])

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

                }

            }
        }
        //getQuestions()
        //getQuestionOptions()
        test()
        //println(questionsIdList+"asd")
        test2()
        //println(questionsIdList)
    }
}

