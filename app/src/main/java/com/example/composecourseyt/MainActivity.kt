package com.example.composecourseyt

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.lifecycleScope
import com.example.composecourseyt.entities.Director
import com.example.composecourseyt.entities.School
import com.example.composecourseyt.entities.Student
import com.example.composecourseyt.entities.Subject
import com.example.composecourseyt.entities.relations.SchoolAndDirector
import com.example.composecourseyt.entities.relations.SchoolWithStudents
import com.example.composecourseyt.entities.relations.StudentSubjectCrossRef
import com.example.composecourseyt.entities.relations.SubjectWithStudents
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dao = SchoolDatabase.getInstance(this).schoolDao

        val directors = listOf(
            Director("Mike Litoris", "Jake Wharton School"),
            Director("Jack Goff", "Kotlin School"),
            Director("Chris P. Chicken", "JetBrains School")
        )
        val schools = listOf(
            School("Jake Wharton School"),
            School("Kotlin School"),
            School("JetBrains School")
        )
        val subjects = listOf(
            Subject("Dating for programmers"),
            Subject("Avoiding depression"),
            Subject("Bug Fix Meditation"),
            Subject("Logcat for Newbies"),
            Subject("How to use Google")
        )
        val students = listOf(
            Student("Beff Jezos", 2, "Kotlin School"),
            Student("Mark Suckerberg", 5, "Jake Wharton School"),
            Student("Gill Bates", 8, "Kotlin School"),
            Student("Donny Jepp", 1, "Kotlin School"),
            Student("Hom Tanks", 2, "JetBrains School")
        )
        val studentSubjectRelations = listOf(
            StudentSubjectCrossRef("Beff Jezos", "Dating for programmers"),
            StudentSubjectCrossRef("Beff Jezos", "Avoiding depression"),
            StudentSubjectCrossRef("Beff Jezos", "Bug Fix Meditation"),
            StudentSubjectCrossRef("Beff Jezos", "Logcat for Newbies"),
            StudentSubjectCrossRef("Mark Suckerberg", "Dating for programmers"),
            StudentSubjectCrossRef("Gill Bates", "How to use Google"),
            StudentSubjectCrossRef("Donny Jepp", "Logcat for Newbies"),
            StudentSubjectCrossRef("Hom Tanks", "Avoiding depression"),
            StudentSubjectCrossRef("Hom Tanks", "Dating for programmers")
        )
        insertToDatabase(
            lifecycleScope,
            dao,
            directors,
            schools,
            subjects,
            students,
            studentSubjectRelations
        )





        setContent {
            val TAG = "content"
            var myList by remember {
                mutableStateOf(listOf<SchoolAndDirector>())
            }
            var myList2 by remember {
                mutableStateOf(listOf<SchoolWithStudents>())
            }
            var myList3 by remember {
                mutableStateOf(listOf<Student>())
            }
            val getStuff1: () -> Unit = {
                Log.d(TAG, "stuff1")
                lifecycleScope.launch(Dispatchers.IO) {
                    Log.d(TAG, "stuff2")
                    val list = async { getSchoolWithDirector(dao) }
                    myList = list.await()
                    Log.d(TAG, "${list.await()}")
                    val list2 = async { getSchoolWithStudents(dao) }
                    myList2 = list2.await()
                    Log.d(TAG, "$myList2")
                    val list3 = async { getStudentsOfSubject(dao) }
                    myList3 = list3.await()[0].students
                    Log.d(TAG, "${myList3}")

                }
            }
            Column() {
                Button(onClick = getStuff1) {
                    Text("Click to get stuff from database")
                }
                LazyColumn {
                    itemsIndexed(myList) { index, string ->
                        Text(text = string.director.directorName)
                    }
                }
                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
                    .background(Color.Red))
                LazyColumn {
                    itemsIndexed(myList2) {
                        index, string ->
                        Log.d(TAG, "lazyColumn2")
                        Text(text = string.students[index].studentName)
                    }
                }
                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
                    .background(Color.Red))
                LazyColumn {
                    itemsIndexed(myList3) {
                            index, string ->
                        Log.d(TAG, "lazyColumn3")
                        Text(text = string.studentName)
                    }
                }
            }

        }
    }
}

fun insertToDatabase(
    lifecycleScope: LifecycleCoroutineScope,
    dao: SchoolDao,
    directors: List<Director>,
    schools: List<School>,
    subjects: List<Subject>,
    students: List<Student>,
    studentSubjectRelations: List<StudentSubjectCrossRef>
) {
    val TAG = "doMyCoroutine"
    lifecycleScope.launch(Dispatchers.IO) {
        Log.d(TAG, "lifecyclescope launched")
        directors.forEach { dao.insertDirector(it) }
        schools.forEach { dao.insertSchool(it) }
        subjects.forEach { dao.insertSubject(it) }
        students.forEach { dao.insertStudent(it) }
        studentSubjectRelations.forEach { dao.insertStudentSubjectCrossRef(it) }

        val schoolWithDirector = dao.getSchoolAndDirectorWithSchoolName("Kotlin School")

        val schoolWithStudents = dao.getSchoolWithStudents("Kotlin School")
    }
}

suspend fun getSchoolWithDirector(dao: SchoolDao): List<SchoolAndDirector> {
    return dao.getSchoolAndDirectorWithSchoolName("Kotlin School")
}

suspend fun getSchoolWithStudents(dao: SchoolDao): List<SchoolWithStudents> {
    return dao.getSchoolWithStudents("Kotlin School")
}

suspend fun getStudentsOfSubject(dao: SchoolDao): List<SubjectWithStudents> {
    return dao.getStudentsOfSubject("Dating for programmers")
}
