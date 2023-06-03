package com.example.danp2023room

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.danp2023room.entities.BookEntity
import com.example.danp2023room.entities.StudentEntity
import com.example.danp2023room.entities.StudentWithUnits
import com.example.danp2023room.entities.UnitEntity
import com.example.danp2023room.entities.UnitStudentCrossRef
import com.example.danp2023room.entities.UnitWithStudents
import com.example.danp2023room.model.AppDatabase
import com.example.danp2023room.model.Repository
import com.example.danp2023room.ui.theme.DANP2023RoomTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.random.Random


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DANP2023RoomTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val context = LocalContext.current
                    val repository = Repository(AppDatabase.getInstance(context.applicationContext))
                    RoomSample(repository)
                }
            }
        }
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun RoomSample(repository: Repository) {
    val TAG: String = "RoomDatabase"
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val fillDataOnClick = {
            fillTables(repository, scope)
        }

        val studentsOnClick: () -> Unit = {
            scope.launch {
                repository.getAllStudents().forEach {
                    Log.d(TAG, it.toString())
                }
            }
        }

        val unitsOnClick: () -> Unit = {
            scope.launch {
                repository.getAllUnits().forEach {
                    Log.d(TAG, it.toString())
                }
            }
        }

        val unitWithStudentsOnClick: () -> Unit = {
            scope.launch {
                repository.getUnitWithStudents() .forEach {
                    Log.d(TAG, it.toString())
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = fillDataOnClick) {
            Text(text = "Fill student & unit tables")
        }

        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = studentsOnClick) {
            Text(text = "Show students")
        }

        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = unitsOnClick) {
            Text(text = "Show units")
        }

        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = unitWithStudentsOnClick) {
            Text(text = "Units with Students")
        }

    }
}

fun fillTables(rep: Repository, scope: CoroutineScope) {

    val students = ArrayList<StudentEntity>()

    for (i in 100..120) {
        val studentEntity = StudentEntity(i, fullname = "Student " + i.toString())
        students.add(studentEntity)
    }

    val units = ArrayList<UnitEntity>()

    for (i in 100..120) {
        val unitEntity = UnitEntity(i, name = "Unit " + i.toString(), credit = Random.nextInt(1, 5))
        units.add(unitEntity)
    }

    scope.launch {
        rep.insertStudents(students)
        rep.insertUnits(units)
    }

    for (i in 100..120) {
        val studentId = Random.nextInt(100, 120)
        val unitId = Random.nextInt(100, 120)

        scope.launch {
            rep.insertUnitStudentCrossRef(UnitStudentCrossRef(studentId, unitId))
        }
    }
}

