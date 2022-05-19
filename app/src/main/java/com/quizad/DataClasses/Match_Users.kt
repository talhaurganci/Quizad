package com.quizad.DataClasses

import org.ktorm.schema.Table
import org.ktorm.schema.int

object Match_Users : Table<Nothing>("match_users"){
    val match_id = int("match_id")
    val user_id = int("user_id")
}
