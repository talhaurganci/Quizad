package com.quizad

data class User_Stats(
        val user_id: Int,
        val total_competitions: Int,
        val total_correct_answers: Int,
        val total_wrong_answers: Int,
        val win_count: Int,
        val lose_count: Int,
        val win_rate: Float,
        val user_record: Int
)
