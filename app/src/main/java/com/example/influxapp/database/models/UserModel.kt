package com.example.influxapp.database.models

import java.util.*

data class UserModel(
    var username: String = "",
    var password_one: String="",
    var password_two:String=""
)