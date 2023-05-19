package com.rodion2236.quiz.db

import android.content.Context
import androidx.room.Room

class DbProfile private constructor(private val context: Context) {

    val appDB: AppDB = Room.databaseBuilder(context, AppDB::class.java, "users.db")
        .fallbackToDestructiveMigration()
        .allowMainThreadQueries()
        .build()

    companion object {
        private var mInstance: DbProfile? = null
        @Synchronized
        fun getInstance(mContext: Context): DbProfile? {
            if (mInstance == null) {
                mInstance = DbProfile(mContext)
            }
            return mInstance
        }
    }
}