package com.quizad.DataClasses

import org.ktorm.schema.Table
import org.ktorm.schema.int

object Match_Questions : Table<Nothing>("match_questions"){
    val question_id = int("question_id")
    val match_id = int("match_id")
}