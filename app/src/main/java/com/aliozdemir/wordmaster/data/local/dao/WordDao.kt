package com.aliozdemir.wordmaster.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aliozdemir.wordmaster.data.local.entity.WordEntity

@Dao
interface WordDao {
    @Query("SELECT * FROM words WHERE isLearned = 0 ORDER BY RANDOM()")
    fun getAllUnlearnedWords(): LiveData<List<WordEntity>>

    @Query("SELECT * FROM words WHERE isLearned = 1")
    fun getAllLearnedWords(): LiveData<List<WordEntity>>

    @Query("UPDATE words SET isLearned = 1 WHERE id = :id")
    suspend fun markAsLearned(id: Int)

    @Query("UPDATE words SET isLearned = 0 WHERE id = :id")
    suspend fun markAsUnlearned(id: Int)

    @Query("SELECT * FROM words WHERE id = :id ")
    fun getWordById(id: Int): LiveData<WordEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(words: List<WordEntity>)
}
