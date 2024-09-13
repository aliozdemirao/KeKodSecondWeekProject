package com.aliozdemir.wordmaster.domain.usecase

import com.aliozdemir.wordmaster.domain.repository.WordRepository
import javax.inject.Inject

class MarkAsUnlearnedUseCase @Inject constructor(
    private val wordRepository: WordRepository
) {
    suspend operator fun invoke(id: Int) {
        wordRepository.markAsUnlearned(id)
    }
}
