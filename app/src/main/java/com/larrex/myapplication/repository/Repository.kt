package com.larrex.myapplication.repository

import com.larrex.myapplication.network.model.IgboApiResponse
import com.larrex.myapplication.network.model.Responce
import kotlinx.coroutines.flow.Flow
import java.util.*

interface Repository {

   suspend fun getIgboApiResponse(keyword: String): Flow<Responce>

}