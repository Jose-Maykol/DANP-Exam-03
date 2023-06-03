package com.example.danp2023room.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.danp2023room.entities.BookEntity
import com.example.danp2023room.entities.StudentEntity
import com.example.danp2023room.entities.UnitEntity
import com.example.danp2023room.entities.UnitStudentCrossRef


@Database(
    entities = [
        StudentEntity::class,
        BookEntity::class,
        UnitEntity::class,
        UnitStudentCrossRef::class],
    version = 12
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun studentDao(): StudentDao
    abstract fun bookDao(): BookDao
    abstract fun unitDao(): UnitDao
    abstract fun unitStudentCrossRefDao(): UnitStudentCrossRefDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "database-name"
                    ).fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance

                }

                return instance
            }
        }
    }
}