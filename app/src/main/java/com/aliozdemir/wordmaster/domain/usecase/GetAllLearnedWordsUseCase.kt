package com.aliozdemir.wordmaster.domain.usecase

import androidx.lifecycle.LiveData
import com.aliozdemir.wordmaster.domain.model.Word
import com.aliozdemir.wordmaster.domain.repository.WordRepository
import javax.inject.Inject

class GetAllLearnedWordsUseCase @Inject constructor(
    private val wordRepository: WordRepository
) {
    operator fun invoke(): LiveData<List<Word>> = wordRepository.getAllLearnedWords()
}
