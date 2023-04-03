package com.larrex.myapplication.ui.viewmodel

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.larrex.myapplication.network.model.IgboSingleWordApiResponse
import com.larrex.myapplication.network.model.Responce
import com.larrex.myapplication.network.model.Status
import com.larrex.myapplication.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.launch
import okhttp3.internal.wait
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private var repository: Repository, private var exoPlayer: ExoPlayer
) : ViewModel() {

    var latestResponse = mutableStateOf<Responce>(
        Responce(
            Status.NOTHING, emptyList(), IgboSingleWordApiResponse(), "Nothing yet"
        )
    )

    val relatedTerms = mutableListOf<String>()
//     val relatedTerms = mutableStateOf("")

    fun getWordMeanings(keyword: String) {

        CoroutineScope(Dispatchers.IO).launch {

            repository.getIgboApiResponse(keyword).collectLatest {

                latestResponse.value = it

            }
        }

    }

    suspend fun getSingleWordMeaning(wordId: String):String {
        val res = CompletableDeferred<String>()

            repository.getSingleWordMeaning(wordId).collectLatest {
//                relatedTerms.add(it.singleWordResponse.word.toString())
                res.complete( it.singleWordResponse.word.toString())
                println(it.singleWordResponse.word.toString()+"ppppppppppppppp")
            }
       return res.await()
    }

    suspend fun playPronunciation(url: String) {

//        val mediaItem = MediaItem()

        exoPlayer.setMediaItem(MediaItem.fromUri(url))
        exoPlayer.playWhenReady = true
        exoPlayer.prepare()

    }

}