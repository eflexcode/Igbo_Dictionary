package com.larrex.myapplication.ui.viewmodel

import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import com.larrex.myapplication.network.model.IgboApiResponse
import com.larrex.myapplication.network.model.Responce
import com.larrex.myapplication.network.model.Status
import com.larrex.myapplication.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private var repository: Repository
) : ViewModel() {

    var latestResponse = mutableStateOf<Responce>(Responce(Status.NOTHING, emptyList(),"Nothing yet"))

    suspend fun getWordMeanings(keyword: String)  {

      return  repository.getIgboApiResponse(keyword).collectLatest {

          latestResponse.value = it

      }
    }

}