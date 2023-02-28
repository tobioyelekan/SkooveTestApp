package com.example.skoovetestapp.di

import com.example.skoovetestapp.data.source.remote.RemoteDataSource
import com.example.skoovetestapp.data.source.remote.RemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteDataSourceModule {
    @Binds
    abstract fun bindsRemoteDataSource(remoteDataSource: RemoteDataSourceImpl): RemoteDataSource
}