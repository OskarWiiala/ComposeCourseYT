package com.example.composecourseyt.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.composecourseyt.entities.Director
import com.example.composecourseyt.entities.School

data class SchoolAndDirector(
    @Embedded
    val school: School,
    @Relation(
        // parentColumn refers to parent entity (School property schoolName)
        parentColumn = "schoolName",
        // entityColumn refers to column schoolName in Director
        entityColumn = "schoolName"
    )
    val director: Director
)
