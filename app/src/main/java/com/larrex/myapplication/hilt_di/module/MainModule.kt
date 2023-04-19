package com.larrex.myapplication.hilt_di.module

import android.app.Application
import androidx.room.Room
import com.larrex.myapplication.hilt_di.repositoryImpl.RepositoryImpl
import com.larrex.myapplication.network.repository.Repository
import com.larrex.myapplication.room.SearchDatabase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class MainModule {

    @Singleton
    @Binds
    abstract fun bindsRepository(repositoryImpl: RepositoryImpl): Repository
companion object{
    @Singleton
    @Provides
    fun provideRoomHistoryDatabase(application: Application): SearchDatabase {
        return Room.databaseBuilder(application, SearchDatabase::class.java, "SearchHistoryDatabase").build()
    }
}
}