package com.example.passwordmanager.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Password::class], version = 1)
abstract class PasswordDatabase : RoomDatabase() {

    abstract fun getPasswordDao(): PasswordDao

    companion object {
        @Volatile
        private var instance: PasswordDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            PasswordDatabase::class.java,
            "password_database"
        ).build()
    }
}
