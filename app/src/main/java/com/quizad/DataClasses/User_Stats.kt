package com.quizad.DataClasses

import org.ktorm.schema.Table
import org.ktorm.schema.float
import org.ktorm.schema.int

object User_Stats : Table<Nothing>("user_stats") {
    val user_id = int("user_id")
    val total_competitions = int("total_competitions")
    val total_correct_answers = int("total_correct_answers")
    val total_wrong_answers = int("total_wrong_answers")
    val win_count = int("win_count")
    val lose_count = int("lose_count")
    val win_rate = float("win_rate")
    val user_record = int("user_record")
}

