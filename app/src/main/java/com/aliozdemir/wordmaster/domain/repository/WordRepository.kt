package com.aliozdemir.wordmaster.domain.repository

import androidx.lifecycle.LiveData
import com.aliozdemir.wordmaster.domain.model.Word

interface WordRepository {
    fun getAllUnlearnedWords(): LiveData<List<Word>>

    fun getAllLearnedWords(): LiveData<List<Word>>

    suspend fun markAsLearned(id: Int)

    suspend fun markAsUnlearned(id: Int)

    fun getWordById(id: Int): LiveData<Word>
}
