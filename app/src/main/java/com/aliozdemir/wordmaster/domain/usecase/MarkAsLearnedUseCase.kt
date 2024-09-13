package com.aliozdemir.wordmaster.domain.usecase

import com.aliozdemir.wordmaster.domain.repository.WordRepository
import javax.inject.Inject

class MarkAsLearnedUseCase @Inject constructor(
    private val wordRepository: WordRepository
) {
    suspend operator fun invoke(id: Int) {
        wordRepository.markAsLearned(id)
    }
}
