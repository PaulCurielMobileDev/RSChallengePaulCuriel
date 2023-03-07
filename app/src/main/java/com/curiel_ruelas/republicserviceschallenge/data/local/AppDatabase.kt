package com.curiel_ruelas.republicserviceschallenge.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.curiel_ruelas.republicserviceschallenge.data.models.Driver
import com.curiel_ruelas.republicserviceschallenge.data.models.Route
import com.curiel_ruelas.republicserviceschallenge.utils.Config

@Database(
    entities = [Driver::class, Route::class],
    version = Config.DB_VERSION,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun allDao(): AllDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase = instance ?: buildDatabase(context)
            .also {
                instance = it
            }

        private fun buildDatabase(context: Context) = Room
            .databaseBuilder(context, AppDatabase::class.java, "theDatabase")
            .fallbackToDestructiveMigration()
            .build()


    }
}