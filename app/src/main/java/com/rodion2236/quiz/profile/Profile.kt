package com.rodion2236.quiz.profile

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
class Profile(@field:ColumnInfo(name = "name") var name: String) {
    @PrimaryKey(autoGenerate = true)

    var id = 0
    var score = 0
    var wallpaper = 0

    override fun toString(): String {
        return "User{id=$id, name='$name', score=$score, wallpaper=$wallpaper}"
    }
}