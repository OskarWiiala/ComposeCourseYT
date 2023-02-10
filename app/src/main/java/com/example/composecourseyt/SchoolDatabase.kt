package com.example.composecourseyt

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.composecourseyt.entities.Director
import com.example.composecourseyt.entities.Student
import com.example.composecourseyt.entities.School
import com.example.composecourseyt.entities.Subject
import com.example.composecourseyt.entities.relations.StudentSubjectCrossRef

@Database(
    entities = [
        School::class,
        Student::class,
        Director::class,
        Subject::class,
        StudentSubjectCrossRef::class
    ],
    version = 1
)
abstract class SchoolDatabase : RoomDatabase() {

    abstract val schoolDao: SchoolDao

    companion object {
        // @Volatile means that whenever the value of this variable is changed,
        // the change is immediately visible to other threads
        @Volatile
        private var INSTANCE: SchoolDatabase? = null

        // Will construct our database
        fun getInstance(context: Context): SchoolDatabase {
            // synchronize will make sure that whenever this block of code is executed,
            // it is executed by only a single thread
            // this refers to the companion object of SchoolDatabase
            synchronized(this) {
                return INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    SchoolDatabase::class.java,
                    "school_db"
                ).build().also {
                    // updates INSTANCE variable to be our freshly built database
                    INSTANCE = it
                }
            }
        }
    }
}