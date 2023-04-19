package com.larrex.myapplication.network.repository

import androidx.room.Query
import com.larrex.myapplication.network.model.IgboApiResponse
import com.larrex.myapplication.network.model.Responce
import com.larrex.myapplication.room.model.SearchHistory
import kotlinx.coroutines.flow.Flow
import java.util.*

interface Repository {

   suspend fun getIgboApiResponse(keyword: String): Flow<Responce>
    fun getSingleWordMeaning(wordId: String): Flow<Responce>
    suspend fun addHistory(searchHistory: SearchHistory)
    suspend fun getAllHistory():Flow<List<SearchHistory>>

    fun deleteHistory(id:Int)

}