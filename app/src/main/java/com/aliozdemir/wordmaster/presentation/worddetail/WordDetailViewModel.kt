package com.aliozdemir.wordmaster.presentation.worddetail

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aliozdemir.wordmaster.domain.model.Word
import com.aliozdemir.wordmaster.domain.usecase.GetWordByIdUseCase
import com.aliozdemir.wordmaster.domain.usecase.MarkAsLearnedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WordDetailViewModel @Inject constructor(
    private val getWordByIdUseCase: GetWordByIdUseCase,
    private val markAsLearnedUseCase: MarkAsLearnedUseCase
) : ViewModel() {
    fun getWordById(id: Int): LiveData<Word> = getWordByIdUseCase(id)

    fun markAsLearned(wordId: Int) {
        viewModelScope.launch {
            markAsLearnedUseCase(wordId)
        }
    }
}
