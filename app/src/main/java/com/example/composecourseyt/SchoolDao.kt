package com.example.composecourseyt

import androidx.room.*
import com.example.composecourseyt.entities.Director
import com.example.composecourseyt.entities.School
import com.example.composecourseyt.entities.relations.SchoolAndDirector

@Dao
interface SchoolDao {
    // suspend because they are executed on a background thread
    // in order to not block the main thread
    // onConflict is when you try to insert a school which already exists
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSchool(school: School)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDirector(director: Director)

    // @Transaction is required to prevent multithreading problems
    @Transaction
    @Query("SELECT * FROM school WHERE schoolName = :schoolName")
    suspend fun getSchoolAndDirectorWithSchoolName(schoolName: String): List<SchoolAndDirector>
}