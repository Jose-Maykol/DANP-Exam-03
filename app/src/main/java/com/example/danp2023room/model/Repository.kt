package com.example.danp2023room.model

import com.example.danp2023room.entities.BookEntity
import com.example.danp2023room.entities.StudentEntity
import com.example.danp2023room.entities.StudentWithBooks
import com.example.danp2023room.entities.StudentWithUnits
import com.example.danp2023room.entities.UnitEntity
import com.example.danp2023room.entities.UnitStudentCrossRef
import com.example.danp2023room.entities.UnitWithStudents

class Repository(private val appDatabase: AppDatabase) {

    suspend fun getAllStudents(): List<StudentEntity> {
        return appDatabase.studentDao().getAll()
    }

    suspend fun getStudentWithBooks(): List<StudentWithBooks> {
        return appDatabase.studentDao().getStudentWithBooks()
    }

    suspend fun insertStudents(studentsEntity: List<StudentEntity>) {
        appDatabase.studentDao().insert(studentsEntity)
    }

    suspend fun insertBooks(books: List<BookEntity>) {
        appDatabase.bookDao().insert(books)
    }

    suspend fun insertBook(book: BookEntity) {
        appDatabase.bookDao().insert(book)
    }

    suspend fun insertUnits(units: List<UnitEntity>) {
        appDatabase.unitDao().insert(units)
    }

    suspend fun getAllBooks(): List<BookEntity> {
        return appDatabase.bookDao().getAll()
    }

    suspend fun getAllUnits(): List<UnitEntity> {
        return appDatabase.unitDao().getAll()
    }

    suspend fun insertUnitStudentCrossRef(unitStudentCrossRef: UnitStudentCrossRef) {
        appDatabase.unitStudentCrossRefDao().insertUnitStudentCrossRef(unitStudentCrossRef)
    }

    /*suspend fun insertStudentWithUnits(studentWithUnits: StudentWithUnits) {
        appDatabase.unitStudentCrossRefDao().insertStudentWithUnits(studentWithUnits)
    }

    suspend fun insertUnitWithStudents(unitWithStudents: UnitWithStudents) {
        appDatabase.unitStudentCrossRefDao().insertUnitWithStudents(unitWithStudents)
    }*/

    suspend fun getStudentWithUnits(): List<StudentWithUnits> {
        return appDatabase.unitStudentCrossRefDao().getStudentWithUnits()
    }

    suspend fun getUnitWithStudents(): List<UnitWithStudents> {
        return  appDatabase.unitStudentCrossRefDao().getUnitWithStudents()
    }
}