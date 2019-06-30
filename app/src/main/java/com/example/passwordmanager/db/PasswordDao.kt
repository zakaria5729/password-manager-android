package com.example.passwordmanager.db

import androidx.room.*

@Dao
interface PasswordDao {
    //suspend method calling is only applicable within coroutines scope

    @Insert
    suspend fun addPassword(password: Password)

    @Update
    suspend fun updatePassword(password: Password)

    @Delete
    suspend fun deletePassword(password: Password)

    @Query("SELECT * FROM password ORDER BY id DESC")
    suspend fun getAllPasswords(): List<Password>
}
