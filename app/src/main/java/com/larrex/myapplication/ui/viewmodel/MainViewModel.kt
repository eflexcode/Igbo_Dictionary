package com.larrex.myapplication.ui.viewmodel

import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import com.larrex.myapplication.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private var repository: Repository
    ) : ViewModel() {


        fun doStuff(){
            repository.getIgboApiResponse()
        }

    }