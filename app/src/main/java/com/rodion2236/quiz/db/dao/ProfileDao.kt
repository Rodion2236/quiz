package com.rodion2236.quiz.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.rodion2236.quiz.profile.Profile

@Dao
interface ProfileDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(profile: Profile?)

    @Update
    fun update(profile: Profile?)

    @get:Query("SELECT * from users")
    val users: List<Profile?>?

    @Query("SELECT * from users where name = :name")
    fun getUserByName(name: String?): Profile?
}