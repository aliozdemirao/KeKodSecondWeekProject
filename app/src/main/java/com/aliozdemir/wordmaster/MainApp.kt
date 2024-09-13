package com.aliozdemir.wordmaster

import android.app.Application
import com.aliozdemir.wordmaster.data.local.dao.WordDao
import com.aliozdemir.wordmaster.di.DatabaseInitializer
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MainApp : Application() {
    @Inject
    lateinit var wordDao: WordDao

    override fun onCreate() {
        super.onCreate()
        DatabaseInitializer.populateDatabase(this, wordDao)
    }
}
