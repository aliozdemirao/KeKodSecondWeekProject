package com.aliozdemir.wordmaster.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aliozdemir.wordmaster.data.local.dao.WordDao
import com.aliozdemir.wordmaster.data.local.entity.WordEntity

@Database(entities = [WordEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun wordDao(): WordDao
}
