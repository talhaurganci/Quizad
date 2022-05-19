package com.quizad.DataClasses

import org.ktorm.schema.*

object Users : Table<Nothing>("users") {
        val id = int("id").primaryKey()
        val firstname = varchar("firstname")
        val lastname = varchar("lastname")
        val photourl = varchar("photourl")
        val googleidtoken = varchar("googleidtoken")
        val serverauthcode = varchar("serverauthcode")
}

