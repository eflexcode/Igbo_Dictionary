package com.larrex.myapplication.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.larrex.myapplication.network.model.IgboSingleWordApiResponse
import com.larrex.myapplication.network.model.Responce
import com.larrex.myapplication.network.model.Status
import com.larrex.myapplication.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private var repository: Repository,
    private var exoPlayer: ExoPlayer
) : ViewModel() {

    var latestResponse = mutableStateOf<Responce>(Responce(Status.NOTHING, emptyList(),
        IgboSingleWordApiResponse(),
        "Nothing yet"))

    suspend fun getWordMeanings(keyword: String)  {

      return  repository.getIgboApiResponse(keyword).collectLatest {

          latestResponse.value = it

      }
    }

    suspend fun playPronunciation(url:String){

//        val mediaItem = MediaItem()

        exoPlayer.setMediaItem(MediaItem.fromUri(url))
        exoPlayer.playWhenReady = true
        exoPlayer.prepare()

    }

}