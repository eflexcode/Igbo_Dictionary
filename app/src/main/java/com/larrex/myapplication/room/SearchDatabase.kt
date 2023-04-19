package com.larrex.myapplication.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.larrex.myapplication.room.model.SearchHistory

@Database(entities = [SearchHistory::class], version = 1)
abstract class SearchDatabase : RoomDatabase() {

   abstract fun dao() : SearchHistoryDao

}