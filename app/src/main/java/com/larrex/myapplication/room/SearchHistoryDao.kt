package com.larrex.myapplication.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.larrex.myapplication.room.model.SearchHistory
import kotlinx.coroutines.flow.Flow

@Dao
interface SearchHistoryDao {

    @Insert
    fun addHistory(searchHistory: SearchHistory)

    @Query("DELETE FROM History WHERE id=:id")
    fun deleteHistory(id:Int)

    @Query("SELECT * FROM History ORDER BY ID DESC")
    fun getAllHistory():Flow<List<SearchHistory>>

}