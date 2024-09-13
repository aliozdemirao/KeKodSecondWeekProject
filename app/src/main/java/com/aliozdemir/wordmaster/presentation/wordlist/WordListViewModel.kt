package com.aliozdemir.wordmaster.presentation.wordlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.aliozdemir.wordmaster.domain.model.Word
import com.aliozdemir.wordmaster.domain.usecase.GetAllUnlearnedWordsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WordListViewModel @Inject constructor(
    private val getAllUnlearnedWordsUseCase: GetAllUnlearnedWordsUseCase,
) : ViewModel() {
    val unlearnedWords: LiveData<List<Word>> = getAllUnlearnedWordsUseCase()
}
