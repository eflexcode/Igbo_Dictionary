package com.larrex.myapplication.hilt_di.repositoryImpl

import android.app.Application
import com.larrex.myapplication.network.IgboApiInterface
import com.larrex.myapplication.network.model.IgboApiResponse
import com.larrex.myapplication.network.model.IgboSingleWordApiResponse
import com.larrex.myapplication.network.model.Responce
import com.larrex.myapplication.network.model.Status
import com.larrex.myapplication.repository.Repository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

//@ActivityRetainedScoped
class RepositoryImpl @Inject constructor(
    private var application: Application,
    private var igboApiInterface: IgboApiInterface
) : Repository {
    override suspend fun getIgboApiResponse(keyword: String): Flow<Responce> {

        //https://igboapi.com/api/v1/words?keyword=please&page=0&range=1&strict=true&dialects=true&examples=true&isStandardIgbo=true&pronunciation=true&nsibidi=false

        return callbackFlow {
            println("this is keyword $keyword")
            val loadingResponse = Responce(Status.LOADING, emptyList(),IgboSingleWordApiResponse() ,"Loading...")
            trySend(loadingResponse)

            val map: MutableMap<String, Any> = HashMap()
            map["keyword"] = keyword.trim()
            map["page"] = 0
            map["range"] = 1
            map["strict"] = true
            map["dialects"] = false
            map["examples"] = true
            map["isStandardIgbo"] = true
            map["nsibidi"] = false

            val apiData =
                igboApiInterface.getMeanings(map).enqueue(object : Callback<List<IgboApiResponse>> {
                    override fun onResponse(
                        call: Call<List<IgboApiResponse>>,
                        response: Response<List<IgboApiResponse>>
                    ) {

                        val successResponse =
                            response.body()?.let { Responce(Status.SUCCESS, it, IgboSingleWordApiResponse(),"") }
                        if (successResponse != null) {
                            trySend(successResponse)
                        }

                        println("this is keyword ${response.body()?.size}")

                    }

                    override fun onFailure(call: Call<List<IgboApiResponse>>, t: Throwable) {
                        val errorResponse = Responce(
                            Status.FAILURE,
                            emptyList(),IgboSingleWordApiResponse(),
                            "Something went wrong please try again"
                        )

                        trySend(errorResponse)
                        println("this is keyword error "+t.message)
                    }
                })
            awaitClose { }
        }

    }

    override suspend fun getSingleWordMeaning(wordId: String): Flow<Responce> {

        return callbackFlow {
            val loadingResponse = Responce(Status.LOADING, emptyList(),IgboSingleWordApiResponse() ,"Loading...")
            trySend(loadingResponse)

            val api = igboApiInterface.getSingleWordMeaning(wordId).enqueue(object : Callback<IgboSingleWordApiResponse>{
                override fun onResponse(
                    call: Call<IgboSingleWordApiResponse>,
                    response: Response<IgboSingleWordApiResponse>
                ) {
                    val successResponse =
                        response.body()?.let { Responce(Status.SUCCESS, emptyList(), it,"") }
                    if (successResponse != null) {
                        trySend(successResponse)
                    }
                }

                override fun onFailure(call: Call<IgboSingleWordApiResponse>, t: Throwable) {
                    val errorResponse = Responce(
                        Status.FAILURE,
                        emptyList(),IgboSingleWordApiResponse(),
                        "Something went wrong please try again"
                    )

                    trySend(errorResponse)
                }

            })
            awaitClose { }
        }

    }
}