package com.aliozdemir.wordmaster.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "words")
data class WordEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val english: String,
    val turkish: String,
    val spanish: String,
    val isLearned: Boolean
)
