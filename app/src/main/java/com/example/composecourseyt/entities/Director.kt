package com.example.composecourseyt.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

// @Entity(tableName = "director_table")
@Entity
data class Director(
    @PrimaryKey(autoGenerate = false)
    val directorName: String,
    val schoolName: String
)
