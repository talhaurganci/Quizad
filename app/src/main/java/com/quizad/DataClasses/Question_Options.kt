package com.quizad.DataClasses

import org.ktorm.jackson.json
import org.ktorm.schema.Table
import org.ktorm.schema.int

object Question_Options : Table<Nothing>("question_options") {
    val question_id = int("question_id")
    val answers = json<List<AnswersMapper>>("answers")
}