package com.alexandrestefani.noteideas.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Users(
    @PrimaryKey
    val id: String,
    val password: String)
