package com.example.composecourseyt.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

// @Entity(tableName = "school_table")
@Entity
data class School(
    @PrimaryKey(autoGenerate = false)
    val schoolName: String
)
