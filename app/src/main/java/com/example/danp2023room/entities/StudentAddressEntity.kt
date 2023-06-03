package com.example.danp2023room.entities

import androidx.room.Embedded
import androidx.room.Relation

data class StudentAddressEntity(
    @Embedded val studentEntity: StudentEntity,
    @Relation(
        parentColumn = "studentId",
        entityColumn = "addressId"
    )
    val addressEntity: AddressEntity
)