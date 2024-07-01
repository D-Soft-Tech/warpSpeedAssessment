package com.example.warpspeedassessment.data.local.database.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.warpspeedassessment.data.local.database.dao.MovieDao
import com.example.warpspeedassessment.data.local.database.dao.MovieDetailsDao
import com.example.warpspeedassessment.data.local.database.entities.MovieDetailsEntity
import com.example.warpspeedassessment.data.local.database.entities.MovieEntity
import com.example.warpspeedassessment.data.local.database.typeConverters.RoomDataTypeConverters
import com.example.warpspeedassessment.utils.AppConstants.DB_NAME
import com.example.warpspeedassessment.utils.AppConstants.DB_VERSION

@Database(
    entities = [MovieEntity::class, MovieDetailsEntity::class],
    version = DB_VERSION,
    exportSchema = false
)
@TypeConverters(
    RoomDataTypeConverters::class
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getMovieDao(): MovieDao
    abstract fun getMovieDetailsDao(): MovieDetailsDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase = INSTANCE ?: synchronized(this) {
            INSTANCE ?: buildDatabase(context).also {
                INSTANCE = it
            }
        }

        private fun buildDatabase(context: Context): AppDatabase =
            Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME)
                .fallbackToDestructiveMigration()
                .build()
    }
}