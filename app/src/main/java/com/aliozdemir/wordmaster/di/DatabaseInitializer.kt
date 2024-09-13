package com.aliozdemir.wordmaster.di

import android.content.Context
import android.content.SharedPreferences
import com.aliozdemir.wordmaster.data.local.dao.WordDao
import com.aliozdemir.wordmaster.data.local.entity.WordEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException
import java.io.InputStream

object DatabaseInitializer {

    private const val PREFS_NAME = "database_prefs"
    private const val KEY_IS_DATABASE_POPULATED = "is_database_populated"

    fun populateDatabase(context: Context, wordDao: WordDao) {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val isDatabasePopulated = sharedPreferences.getBoolean(KEY_IS_DATABASE_POPULATED, false)

        if (!isDatabasePopulated) {
            val jsonString = loadJsonFromAssets(context, "word.json")
            jsonString?.let {
                val wordEntities = parseJsonToWordEntities(it)
                CoroutineScope(Dispatchers.IO).launch {
                    wordDao.insertAll(wordEntities)

                    sharedPreferences.edit().putBoolean(KEY_IS_DATABASE_POPULATED, true).apply()
                }
            }
        }
    }
}

fun loadJsonFromAssets(context: Context, fileName: String): String? {
    return try {
        val inputStream: InputStream = context.assets.open(fileName)
        val size: Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        String(buffer, Charsets.UTF_8)
    } catch (e: IOException) {
        e.printStackTrace()
        null
    }
}

fun parseJsonToWordEntities(jsonString: String): List<WordEntity> {
    val gson = Gson()
    val listType = object : TypeToken<List<WordEntity>>() {}.type
    return gson.fromJson(jsonString, listType)
}
