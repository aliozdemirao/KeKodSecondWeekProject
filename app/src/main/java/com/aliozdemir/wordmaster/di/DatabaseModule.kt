package com.aliozdemir.wordmaster.di

import android.content.Context
import androidx.room.Room
import com.aliozdemir.wordmaster.data.local.dao.WordDao
import com.aliozdemir.wordmaster.data.local.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context,
    ): AppDatabase =
        Room
            .databaseBuilder(
                context,
                AppDatabase::class.java,
                "app_database",
            ).build()

    @Provides
    @Singleton
    fun provideWordDao(appDatabase: AppDatabase): WordDao = appDatabase.wordDao()
}
