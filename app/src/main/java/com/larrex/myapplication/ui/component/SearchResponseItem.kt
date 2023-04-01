package com.larrex.myapplication.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.larrex.myapplication.Util
import com.larrex.myapplication.network.model.IgboApiResponse
import com.larrex.myapplication.ui.theme.green

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SearchResponseItem(reponse: IgboApiResponse) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp, top = 5.dp, bottom = 0.dp)
    ) {

        Column() {
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
                horizontalArrangement = Arrangement.Center, modifier = Modifier.padding(bottom = 5.dp)
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
                    text =  it,
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
//
//            reponse.relatedTerms.forEach {
//                Text(
//                    text =  it,
//                    textAlign = TextAlign.Start,
//                    fontSize = 15.sp,
//                    color = Color.Black,
//                    fontWeight = FontWeight.Normal,
//                    fontFamily = Util.quicksand,
//                    style = TextStyle.Default,
//                    modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)
//                )
//            }
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
//                    text =  it,
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

    }
    FlowRow(
        modifier = Modifier.padding(
            start = 5.dp,
            end = 0.dp,
            top = 5.dp,
            bottom = 5.dp
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

}