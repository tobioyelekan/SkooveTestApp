package com.example.skoovetestapp.di

import android.content.Context
import com.example.skoovetestapp.data.cache.SkooveSongDatabase
import com.example.skoovetestapp.data.cache.dao.SongDataDao
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
    fun providesDatabase(@ApplicationContext context: Context): SkooveSongDatabase {
        return SkooveSongDatabase.getDatabase(context)
    }

    @Provides
    @Singleton
    fun providesRateDao(database: SkooveSongDatabase): SongDataDao {
        return database.songDataDao()
    }
}