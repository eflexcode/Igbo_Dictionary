package com.larrex.myapplication.ui.viewmodel

import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import com.larrex.myapplication.network.model.Responce
import com.larrex.myapplication.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private var repository: Repository
) : ViewModel() {


    suspend fun getWordMeanings(keyword: String) : Flow<Responce> {
      return  repository.getIgboApiResponse(keyword)
    }

}