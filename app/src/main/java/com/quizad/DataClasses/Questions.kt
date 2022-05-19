package com.quizad.DataClasses

import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object Questions : Table<Nothing>("Questions"){
    val id = int("id").primaryKey()
    val question_text = varchar("question_text")
    val difficulty = int("difficulty")
}

