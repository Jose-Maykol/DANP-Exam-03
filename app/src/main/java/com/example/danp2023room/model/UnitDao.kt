package com.example.danp2023room.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.danp2023room.entities.UnitEntity
import com.example.danp2023room.entities.UnitWithStudents

@Dao
interface UnitDao {
    @Query("select * from unit")
    suspend fun getAll(): List<UnitEntity>

    @Insert
    suspend fun insert(unitEntity: UnitEntity)

    @Insert
    suspend fun insert(unitEntity: List<UnitEntity>)

    @Delete
    suspend fun delete(unitEntity: UnitEntity)
}