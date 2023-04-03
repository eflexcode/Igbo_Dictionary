package com.larrex.myapplication.ui.component

import android.media.AudioManager
import android.media.MediaPlayer
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.IconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.larrex.myapplication.R
import com.larrex.myapplication.Util
import com.larrex.myapplication.network.model.IgboApiResponse
import com.larrex.myapplication.ui.theme.grayLight
import com.larrex.myapplication.ui.theme.green
import com.larrex.myapplication.ui.theme.greenLight
import com.larrex.myapplication.ui.viewmodel.MainViewModel
import kotlinx.coroutines.launch
import java.io.IOException

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SearchResponseItem(reponse: IgboApiResponse, viewModel: MainViewModel) {

    val context = LocalContext.current
    val scope = rememberCoroutineScope()

//    if (reponse.relatedTerms.size > 0) {
//        reponse.relatedTerms.forEach {
//            viewModel.getWordMeanings(it)
//        }
//    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {

        Column(modifier = Modifier
            .padding(start = 10.dp, end = 10.dp, top = 5.dp, bottom = 5.dp)) {
            Text(
                text = reponse.nsibidi.toString(),
                textAlign = TextAlign.Start,
                fontSize = 30.sp,
                color = green,
                fontWeight = FontWeight.Light,
                style = TextStyle.Default,
                fontFamily = Util.nsibidi,
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.padding(bottom = 5.dp)
            ) {
                Text(
                    text = reponse.word.toString(),
                    textAlign = TextAlign.Start,
                    fontSize = 30.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontFamily = Util.quicksand,
                    style = TextStyle.Default,
                )
                Text(
                    text = Util.getWordClassType(reponse.wordClass.toString()),
                    textAlign = TextAlign.Center,
                    fontSize = 13.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Light,
                    style = TextStyle.Default,
                    fontStyle = FontStyle.Italic,
                    fontFamily = Util.quicksand,
                    modifier = Modifier.padding(start = 5.dp)
                )
                IconButton(
                    onClick = {
                        scope.launch {

//                            val mediaPlayer = MediaPlayer()
//                            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)
//                            try {
//                                mediaPlayer.setDataSource(reponse.pronunciation)
//                                mediaPlayer.prepare()
//                                mediaPlayer.start()
//                                Toast.makeText(
//                                    context,
//                                    "Playing pronunciation for " + reponse.word,
//                                    Toast.LENGTH_SHORT
//                                ).show()
//                            } catch (e: IOException) {
//                                e.printStackTrace()
//                                Toast.makeText(
//                                    context,
//                                    "Failed to play pronunciation for " + reponse.word,
//                                    Toast.LENGTH_SHORT
//                                ).show()
//                            }

                            reponse.pronunciation?.let { viewModel.playPronunciation(it) }
                            Toast.makeText(
                                context,
                                "Playing pronunciation for " + reponse.word,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    },
                    modifier = Modifier
                        .size(30.dp)
                        .padding(start = 5.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_volume),
                        contentDescription = null,
                        modifier = Modifier.size(30.dp),
                        tint = greenLight
                    )
                }
            }
            Row(
                modifier = Modifier.padding(
                    start = 0.dp,
                    end = 0.dp,
                    top = 0.dp,
                    bottom = 0.dp
                )
            ) {

                if (reponse.attributes?.isStandardIgbo == true) {

                    ProviderChip(
                        chipText = "Standard Igbo",
                        onChipSelected = {})
                }
                if (reponse.attributes?.isAccented == true) {
                    ProviderChip(
                        chipText = "Accented",
                        onChipSelected = {})
                }
                if (reponse.attributes?.isSlang == true) {
                    ProviderChip(
                        chipText = "Slang",
                        onChipSelected = {})
                }
                if (reponse.attributes?.isConstructedTerm == true) {
                    ProviderChip(
                        chipText = "Constructed Term",
                        onChipSelected = {})
                }
                if (reponse.attributes?.isBorrowedTerm == true) {
                    ProviderChip(
                        chipText = "Borrowed Term",
                        onChipSelected = {})
                }
                if (reponse.attributes?.isStem == true) {
                    ProviderChip(
                        chipText = "Stem",
                        onChipSelected = {})
                }
                if (reponse.attributes?.isCommon == true) {
                    ProviderChip(
                        chipText = "Common",
                        onChipSelected = {})
                }

            }

//            PronunciationItem(play = {  }, isPlaying = false)

            reponse.definitions.forEachIndexed { index, defination ->
                val count = index + 1
                Text(
                    text = "$count. " + defination,
                    textAlign = TextAlign.Start,
                    fontSize = 15.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Normal,
                    fontFamily = Util.quicksand,
                    style = TextStyle.Default,
                    modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)
                )
            }

            Text(
                text = "variations:",
                textAlign = TextAlign.Start,
                fontSize = 20.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontFamily = Util.quicksand,
                style = TextStyle.Default,
                modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)
            )

            reponse.variations.forEach {
                Text(
                    text = it,
                    textAlign = TextAlign.Start,
                    fontSize = 15.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Normal,
                    fontFamily = Util.quicksand,
                    style = TextStyle.Default,
                    modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)
                )
            }
            Text(
                text = "Examples:",
                textAlign = TextAlign.Start,
                fontSize = 20.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontFamily = Util.quicksand,
                style = TextStyle.Default,
                modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)
            )

            for (example in reponse.examples) {
                Text(
                    text = "English: " + example.english.toString(),
                    textAlign = TextAlign.Start,
                    fontSize = 15.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Normal,
                    fontFamily = Util.quicksand,
                    style = TextStyle.Default,
                    modifier = Modifier.padding(top = 2.dp, bottom = 2.dp)
                )
                Text(
                    text = "Igbo: " + example.igbo.toString(),
                    textAlign = TextAlign.Start,
                    fontSize = 15.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Normal,
                    fontFamily = Util.quicksand,
                    style = TextStyle.Default,
                    modifier = Modifier.padding(top = 2.dp, bottom = 2.dp)
                )


            }


//            Text(
//                text = "Related Terms:",
//                textAlign = TextAlign.Start,
//                fontSize = 20.sp,
//                color = Color.Black,
//                fontWeight = FontWeight.Bold,
//                fontFamily = Util.quicksand,
//                style = TextStyle.Default,
//                modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)
//            )

//            reponse.relatedTerms.forEach {
//                viewModel.getSingleWordMeaning(it)
//            }

//            FlowRow() {
//                reponse.relatedTerms.forEach {
//                    val relatedTerm = mutableStateOf("")
//
////    val word = viewModel.relatedTerms.value
//                    scope.launch { relatedTerm.value = viewModel.getSingleWordMeaning(it) }
//                    Text(
//                        text = relatedTerm.value,
//                        textAlign = TextAlign.Start,
//                        fontSize = 15.sp,
//                        color = green,
//                        fontWeight = FontWeight.Normal,
//                        fontFamily = Util.quicksand,
//                        style = TextStyle(textDecoration = TextDecoration.Underline),
//                        modifier = Modifier.padding(top = 3.dp, bottom = 5.dp, end = 3.dp)
//                    )
//                }
//
//            }
//
//            Text(
//                text = "Word Stems:",
//                textAlign = TextAlign.Start,
//                fontSize = 20.sp,
//                color = Color.Black,
//                fontWeight = FontWeight.Bold,
//                fontFamily = Util.quicksand,
//                style = TextStyle.Default,
//                modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)
//            )
//
//            reponse.stems.forEach {
//                Text(
//                    text = it,
//                    textAlign = TextAlign.Start,
//                    fontSize = 15.sp,
//                    color = Color.Black,
//                    fontWeight = FontWeight.Normal,
//                    fontFamily = Util.quicksand,
//                    style = TextStyle.Default,
//                    modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)
//                )
//            }

        }
//        Spacer(modifier = Modifier
//            .height(0.5.dp)
//            .fillMaxWidth()
//            .background(grayLight))
    }


}