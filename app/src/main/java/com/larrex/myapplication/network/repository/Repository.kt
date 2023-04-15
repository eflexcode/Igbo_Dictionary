package com.larrex.myapplication.network.repository

import com.larrex.myapplication.network.model.IgboApiResponse
import com.larrex.myapplication.network.model.Responce
import kotlinx.coroutines.flow.Flow
import java.util.*

interface Repository {

   suspend fun getIgboApiResponse(keyword: String): Flow<Responce>
    fun getSingleWordMeaning(wordId: String): Flow<Responce>

}