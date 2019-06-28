package com.example.passwordmanager.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PasswordDao {
    @Insert
    suspend fun addPassword(password: Password) //suspend method calling is only applicable within coroutines scope

    @Insert
    suspend fun addMultiplePasswords(vararg password: Password)

    @Query("SELECT * FROM password")
    suspend fun getAllPasswords(): List<Password>
}