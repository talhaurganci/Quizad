package com.quizad.DataClasses

import org.ktorm.schema.Table
import org.ktorm.schema.int

object Matches : Table<Nothing>("matches"){
    val id = int("id").primaryKey()
}
