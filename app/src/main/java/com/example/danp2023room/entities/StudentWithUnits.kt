package com.example.danp2023room.entities

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class StudentWithUnits(
    @Embedded val studentEntity: StudentEntity,
    @Relation(
        parentColumn = "studentId",
        entityColumn = "unitId",
        associateBy = Junction(UnitStudentCrossRef::class)
    )
    val units: List<UnitEntity>
)