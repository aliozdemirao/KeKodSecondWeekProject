package com.aliozdemir.wordmaster.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.aliozdemir.wordmaster.data.local.dao.WordDao
import com.aliozdemir.wordmaster.data.local.entity.WordEntity
import com.aliozdemir.wordmaster.data.mapper.toWord
import com.aliozdemir.wordmaster.data.mapper.toWordList
import com.aliozdemir.wordmaster.domain.model.Word
import com.aliozdemir.wordmaster.domain.repository.WordRepository
import javax.inject.Inject

class WordRepositoryImpl @Inject constructor(
    private val wordDao: WordDao
) : WordRepository {
    override fun getAllUnlearnedWords(): LiveData<List<Word>> = wordDao.getAllUnlearnedWords().map { it.toWordList() }

    override fun getAllLearnedWords(): LiveData<List<Word>> = wordDao.getAllLearnedWords().map { it.toWordList() }

    override suspend fun markAsLearned(id: Int) {
        wordDao.markAsLearned(id)
    }

    override suspend fun markAsUnlearned(id: Int) {
        wordDao.markAsUnlearned(id)
    }

    override fun getWordById(id: Int): LiveData<Word> = wordDao.getWordById(id).map { it.toWord() }
}
