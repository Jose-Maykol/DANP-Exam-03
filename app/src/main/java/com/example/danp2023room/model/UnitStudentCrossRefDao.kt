package com.example.danp2023room.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.danp2023room.entities.StudentWithUnits
import com.example.danp2023room.entities.UnitStudentCrossRef
import com.example.danp2023room.entities.UnitWithStudents


@Dao
interface UnitStudentCrossRefDao {

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUnitStudentCrossRef(unitStudentCrossRef: UnitStudentCrossRef)

    /*@Transaction
    @Insert
    suspend fun insertStudentWithUnits(studentWithUnits: StudentWithUnits)

    @Transaction
    @Insert
    suspend fun insertUnitWithStudents(unitWithStudents: UnitWithStudents)*/

    @Transaction
    @Query("SELECT * FROM unit")
    suspend fun getUnitWithStudents(): List<UnitWithStudents>

    @Transaction
    @Query("SELECT * FROM student")
    suspend fun getStudentWithUnits(): List<StudentWithUnits>
}