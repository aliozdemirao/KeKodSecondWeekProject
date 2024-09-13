package com.aliozdemir.wordmaster.data.mapper

import com.aliozdemir.wordmaster.data.local.entity.WordEntity
import com.aliozdemir.wordmaster.domain.model.Word

fun WordEntity.toWord(): Word = Word(id, english, turkish, spanish, isLearned)

fun List<WordEntity>.toWordList(): List<Word> = this.map { it.toWord() }