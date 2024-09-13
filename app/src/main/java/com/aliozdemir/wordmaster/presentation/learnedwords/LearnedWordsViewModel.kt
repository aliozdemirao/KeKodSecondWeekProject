package com.aliozdemir.wordmaster.presentation.learnedwords

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.aliozdemir.wordmaster.domain.model.Word
import com.aliozdemir.wordmaster.domain.usecase.GetAllLearnedWordsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LearnedWordsViewModel @Inject constructor(
    private val getAllLearnedWordsUseCase: GetAllLearnedWordsUseCase
) : ViewModel() {
    val learnedWords: LiveData<List<Word>> = getAllLearnedWordsUseCase()
}
