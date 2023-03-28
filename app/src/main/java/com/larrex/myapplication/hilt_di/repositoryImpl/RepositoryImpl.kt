package com.larrex.myapplication.hilt_di.repositoryImpl

import android.app.Application
import com.larrex.myapplication.network.IgboApiInterface
import com.larrex.myapplication.network.model.IgboApiResponse
import com.larrex.myapplication.network.model.Responce
import com.larrex.myapplication.network.model.Status
import com.larrex.myapplication.repository.Repository
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.Objects
import javax.inject.Inject

@ActivityRetainedScoped
class RepositoryImpl @Inject constructor(
    private var application: Application,
    private var igboApiInterface: IgboApiInterface
) : Repository {
    override suspend fun getIgboApiResponse(keyword: String): Flow<Responce> {

        //https://igboapi.com/api/v1/words?keyword=please&page=0&range=1&strict=true&dialects=true&examples=true&isStandardIgbo=true&pronunciation=true&nsibidi=false

        return flow {

            val loadingResponse  = Responce(Status.LOADING,"Loading...")
            emit(loadingResponse)

            val map: MutableMap<String, Any> = HashMap()
            map["keyword"] = keyword
            map["page"] = 0
            map["range"] = 1
            map["strict"] = true
            map["dialects"] = false
            map["examples"] = true
            map["isStandardIgbo"] = true
            map["nsibidi"] = false

            val apiData = igboApiInterface.getMeanings().execute()

            if (apiData.isSuccessful) {

                val successResponse  = Responce(Status.SUCCESS,apiData.body())

                emit(successResponse)

            } else {
                val errorResponse  = Responce(Status.FAILURE,"Something went wrong please try again")

                emit(errorResponse)
            }
        }

    }
}