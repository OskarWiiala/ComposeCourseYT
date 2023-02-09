package com.example.composecourseyt.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.composecourseyt.entities.School
import com.example.composecourseyt.entities.Student

data class SchoolWithStudents(
    @Embedded val school: School,
    @Relation(
        // schoolName is the primary key of School
        parentColumn = "schoolName",
        // schoolName refers to the column of table Student
        entityColumn = "schoolName"
    )
    val students: List<Student>
)
