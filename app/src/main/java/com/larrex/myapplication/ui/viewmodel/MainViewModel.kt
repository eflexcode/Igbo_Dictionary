package com.larrex.myapplication.ui.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.larrex.myapplication.network.model.IgboSingleWordApiResponse
import com.larrex.myapplication.network.model.Responce
import com.larrex.myapplication.network.model.Status
import com.larrex.myapplication.network.repository.Repository
import com.larrex.myapplication.room.model.SearchHistory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
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
    var singleLatestResponse = mutableStateOf<Responce>(
        Responce(
            Status.NOTHING, emptyList(), IgboSingleWordApiResponse(), "Nothing yet"
        )
    )

    val history = mutableStateListOf<SearchHistory>()
//     val relatedTerms = mutableStateOf("")

    fun getWordMeanings(keyword: String) {

        CoroutineScope(Dispatchers.IO).launch {

            repository.getIgboApiResponse(keyword).collectLatest {

                latestResponse.value = it

            }
        }

    }

    suspend fun getSingleWordMeaning(wordId: String) {

        repository.getSingleWordMeaning(wordId).collectLatest {
//                relatedTerms.add(it.singleWordResponse.word.toString())
            println(it.singleWordResponse.word.toString() + "ppppppppppppppp")
            singleLatestResponse.value = it
        }
    }

    suspend fun playPronunciation(url: String) {

//        val mediaItem = MediaItem()

        exoPlayer.setMediaItem(MediaItem.fromUri(url))
        exoPlayer.playWhenReady = true
        exoPlayer.prepare()

    }

    fun addHistory(searchHistory: SearchHistory) {

        CoroutineScope(Dispatchers.IO).launch {
            repository.addHistory(searchHistory)

        }

    }

    fun getAllHistory() {
        CoroutineScope(Dispatchers.IO).launch {

            repository.getAllHistory().collectLatest {
                history.clear()
                history.addAll(it)
            }
        }
    }

    fun deleteHistory(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {

            repository.deleteHistory(id)

        }
    }

}