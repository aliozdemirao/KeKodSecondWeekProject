package com.aliozdemir.wordmaster.di

import com.aliozdemir.wordmaster.data.repository.WordRepositoryImpl
import com.aliozdemir.wordmaster.domain.repository.WordRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindWordRepository(wordRepositoryImpl: WordRepositoryImpl): WordRepository
}
