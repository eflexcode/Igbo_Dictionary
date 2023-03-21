package com.larrex.myapplication.hilt_di.module

import com.larrex.myapplication.hilt_di.repositoryImpl.RepositoryImpl
import com.larrex.myapplication.repository.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class MainModule {

    @Binds
    @Singleton
   abstract fun bindsRepository(repositoryImpl: RepositoryImpl): Repository

}