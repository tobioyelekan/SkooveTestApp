package com.example.skoovetestapp.data.cache

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.skoovetestapp.data.cache.dao.SongDataDao
import com.example.skoovetestapp.data.cache.entity.SongDataEntity

@Database(
    entities = [SongDataEntity::class],
    version = 1,
    exportSchema = false
)
abstract class SkooveSongDatabase : RoomDatabase() {
    abstract fun songDataDao(): SongDataDao

    companion object {
        private var instance: SkooveSongDatabase? = null

        fun getDatabase(context: Context): SkooveSongDatabase {
            if (instance == null) {
                synchronized(SkooveSongDatabase::class.java) {}
                instance =
                    Room.databaseBuilder(context, SkooveSongDatabase::class.java, "AppDatabase")
                        .fallbackToDestructiveMigration()
                        .build()
            }

            return instance!!
        }
    }
}