package com.rodion2236.quiz.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rodion2236.quiz.db.dao.ProfileDao
import com.rodion2236.quiz.profile.Profile

@Database(entities = [Profile::class], version = 2, exportSchema = false)
abstract class AppDB : RoomDatabase() {

    abstract fun profileDao(): ProfileDao

    companion object {
        @Volatile
        private var appDB: AppDB? = null

        fun getInstance(context: Context): AppDB {
            return appDB?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDB::class.java,
                    "mydatabase"
                ).build()
                appDB = instance
                instance
            }
        }
    }
}