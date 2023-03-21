package com.larrex.myapplication.hilt_di.module

import com.larrex.myapplication.hilt_di.repositoryImpl.RepositoryImpl
import com.larrex.myapplication.repository.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class MainModule {

    @Singleton
    @Binds
    abstract fun bindsRepository(repositoryImpl: RepositoryImpl): Repository

}