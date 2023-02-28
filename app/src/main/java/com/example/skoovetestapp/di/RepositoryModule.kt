package com.example.skoovetestapp.di

import com.example.skoovetestapp.data.repository.SongRepository
import com.example.skoovetestapp.data.repository.SongRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindsSongRepository(repository: SongRepositoryImpl): SongRepository
}