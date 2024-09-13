package com.aliozdemir.wordmaster.presentation.learnedworddetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aliozdemir.wordmaster.domain.model.Word
import com.aliozdemir.wordmaster.domain.usecase.GetWordByIdUseCase
import com.aliozdemir.wordmaster.domain.usecase.MarkAsUnlearnedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LearnedWordDetailViewModel @Inject constructor(
    private val getWordByIdUseCase: GetWordByIdUseCase,
    private val markAsUnlearnedUseCase: MarkAsUnlearnedUseCase
) : ViewModel() {
    fun getWordById(id: Int): LiveData<Word> = getWordByIdUseCase(id)

    fun markAsUnlearned(wordId: Int) {
        viewModelScope.launch {
            markAsUnlearnedUseCase(wordId)
        }
    }
}
