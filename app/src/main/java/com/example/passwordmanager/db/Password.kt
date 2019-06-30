package com.example.passwordmanager.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Password(
    val usernameOrEmail: String,
    val password: String,
    val accountName: String
) : Serializable {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}