package com.rodion2236.quiz.db

import android.content.Context
import androidx.room.Room

class DbProfile private constructor(private val context: Context) {

    val appDB: AppDB = Room.databaseBuilder(context, AppDB::class.java, "users.db")
        .fallbackToDestructiveMigration()
        .allowMainThreadQueries()
        .build()

    companion object {
        private var instance: DbProfile? = null
        @Synchronized
        fun getInstance(context: Context): DbProfile? {
            if (instance == null) {
                instance = DbProfile(context)
            }
            return instance
        }
    }
}