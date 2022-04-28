package com.quizad

import org.json.JSONArray

data class Question_Options(
        val question_id: Int,
        val answers: JSONArray
)
