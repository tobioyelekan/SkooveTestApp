package com.example.skoovetestapp.di

import com.example.skoovetestapp.data.source.local.SongLocalDataSource
import com.example.skoovetestapp.data.source.local.SongLocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalDataSourceModule {
    @Binds
    abstract fun bindsLocalDataSource(localDataSource: SongLocalDataSourceImpl): SongLocalDataSource
}