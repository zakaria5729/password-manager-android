package com.example.passwordmanager.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface PasswordDao {
    //suspend method calling is only applicable within coroutines scope

    @Insert
    suspend fun addPassword(password: Password)

    @Update
    suspend fun updatePassword(password: Password)

    @Query("SELECT * FROM password ORDER BY id DESC")
    suspend fun getAllPasswords(): List<Password>
}
