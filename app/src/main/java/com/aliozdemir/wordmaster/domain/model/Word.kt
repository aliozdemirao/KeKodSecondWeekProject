package com.aliozdemir.wordmaster.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Word(
    val id: Int,
    val english: String,
    val turkish: String,
    val spanish: String,
    val isLearned: Boolean,
) : Parcelable
