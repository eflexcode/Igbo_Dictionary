package com.larrex.myapplication.ui

import android.os.Handler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.larrex.myapplication.R
import com.larrex.myapplication.Util
import com.larrex.myapplication.network.model.Status
import com.larrex.myapplication.room.model.SearchHistory
import com.larrex.myapplication.ui.component.SearchHistoryItem
import com.larrex.myapplication.ui.component.SearchResponseItem
import com.larrex.myapplication.ui.theme.grayLight
import com.larrex.myapplication.ui.theme.green
import com.larrex.myapplication.ui.viewmodel.MainViewModel
import kotlinx.coroutines.launch

@OptIn(
    ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class, ExperimentalPagerApi::class
)
@Composable
fun HomeScreen(viewModel: MainViewModel) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(Color.Transparent, true)
    var searchValue by remember {
        mutableStateOf(TextFieldValue(""))
    }
    val keyController = LocalSoftwareKeyboardController.current

    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    val handler = Handler()
    var showProcess by remember { mutableStateOf(false) }
    val titles = listOf("Search Result", "History")
    var pagerState = rememberPagerState()
    val context = LocalContext.current
    viewModel.getAllHistory()
//    val wordMeaning by viewModel.getWordMeanings(searchValue.text)
//        .collectAsState(initial = Responce(Status.NOTHING, null))

    var state by remember { mutableStateOf(0) }

//    if (sheetState.isVisible) {
//        ModalBottomSheet(
//            onDismissRequest = { /*TODO*/ },
//            sheetState = sheetState,
//            containerColor = Color.White, modifier = Modifier.height(600.dp)
//        ) {
//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
//            ) {
//
//                Column(
//                    modifier = Modifier
//                        .padding(start = 10.dp, end = 10.dp, top = 5.dp, bottom = 5.dp)
//                ) {
//                    Text(
//                        text = viewModel.singleLatestResponse.value.singleWordResponse.nsibidi.toString(),
//                        textAlign = TextAlign.Start,
//                        fontSize = 30.sp,
//                        color = green,
//                        fontWeight = FontWeight.Light,
//                        style = TextStyle.Default,
//                        fontFamily = Util.nsibidi,
//                    )
//                    Row(
//                        verticalAlignment = Alignment.CenterVertically,
//                        horizontalArrangement = Arrangement.Center,
//                        modifier = Modifier.padding(bottom = 5.dp)
//                    ) {
//                        Text(
//                            text = viewModel.singleLatestResponse.value.singleWordResponse.word.toString(),
//                            textAlign = TextAlign.Start,
//                            fontSize = 30.sp,
//                            color = Color.Black,
//                            fontWeight = FontWeight.Bold,
//                            fontFamily = Util.quicksand,
//                            style = TextStyle.Default,
//                        )
//                        Text(
//                            text = Util.getWordClassType(viewModel.singleLatestResponse.value.singleWordResponse.wordClass.toString()),
//                            textAlign = TextAlign.Center,
//                            fontSize = 13.sp,
//                            color = Color.Black,
//                            fontWeight = FontWeight.Light,
//                            style = TextStyle.Default,
//                            fontStyle = FontStyle.Italic,
//                            fontFamily = Util.quicksand,
//                            modifier = Modifier.padding(start = 5.dp)
//                        )
//                        androidx.compose.material.IconButton(
//                            onClick = {
//                                scope.launch {
//                                    viewModel.singleLatestResponse.value.singleWordResponse.pronunciation?.let {
//                                        viewModel.playPronunciation(
//                                            it
//                                        )
//                                    }
//                                    Toast.makeText(
//                                        context,
//                                        "Playing pronunciation for " + viewModel.singleLatestResponse.value.singleWordResponse.word,
//                                        Toast.LENGTH_SHORT
//                                    ).show()
//                                }
//                            },
//                            modifier = Modifier
//                                .size(30.dp)
//                                .padding(start = 5.dp)
//                        ) {
//                            Icon(
//                                painter = painterResource(id = R.drawable.ic_volume),
//                                contentDescription = null,
//                                modifier = Modifier.size(30.dp),
//                                tint = greenLight
//                            )
//                        }
//                    }
//                    Row(
//                        modifier = Modifier.padding(
//                            start = 0.dp,
//                            end = 0.dp,
//                            top = 0.dp,
//                            bottom = 0.dp
//                        )
//                    ) {
//
//                        if (viewModel.singleLatestResponse.value.singleWordResponse.attributes?.isStandardIgbo == true) {
//
//                            ProviderChip(
//                                chipText = "Standard Igbo",
//                                onChipSelected = {})
//                        }
//                        if (viewModel.singleLatestResponse.value.singleWordResponse.attributes?.isAccented == true) {
//                            ProviderChip(
//                                chipText = "Accented",
//                                onChipSelected = {})
//                        }
//                        if (viewModel.singleLatestResponse.value.singleWordResponse.attributes?.isSlang == true) {
//                            ProviderChip(
//                                chipText = "Slang",
//                                onChipSelected = {})
//                        }
//                        if (viewModel.singleLatestResponse.value.singleWordResponse.attributes?.isConstructedTerm == true) {
//                            ProviderChip(
//                                chipText = "Constructed Term",
//                                onChipSelected = {})
//                        }
//                        if (viewModel.singleLatestResponse.value.singleWordResponse.attributes?.isBorrowedTerm == true) {
//                            ProviderChip(
//                                chipText = "Borrowed Term",
//                                onChipSelected = {})
//                        }
//                        if (viewModel.singleLatestResponse.value.singleWordResponse.attributes?.isStem == true) {
//                            ProviderChip(
//                                chipText = "Stem",
//                                onChipSelected = {})
//                        }
//                        if (viewModel.singleLatestResponse.value.singleWordResponse.attributes?.isCommon == true) {
//                            ProviderChip(
//                                chipText = "Common",
//                                onChipSelected = {})
//                        }
//
//                    }
//
////            PronunciationItem(play = {  }, isPlaying = false)
//
//                    viewModel.singleLatestResponse.value.singleWordResponse.definitions.forEachIndexed { index, defination ->
//                        val count = index + 1
//                        Text(
//                            text = "$count. " + defination,
//                            textAlign = TextAlign.Start,
//                            fontSize = 15.sp,
//                            color = Color.Black,
//                            fontWeight = FontWeight.Normal,
//                            fontFamily = Util.quicksand,
//                            style = TextStyle.Default,
//                            modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)
//                        )
//                    }
//
//                    Text(
//                        text = "variations:",
//                        textAlign = TextAlign.Start,
//                        fontSize = 20.sp,
//                        color = Color.Black,
//                        fontWeight = FontWeight.Bold,
//                        fontFamily = Util.quicksand,
//                        style = TextStyle.Default,
//                        modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)
//                    )
//
//                    viewModel.singleLatestResponse.value.singleWordResponse.variations.forEach {
//                        Text(
//                            text = it,
//                            textAlign = TextAlign.Start,
//                            fontSize = 15.sp,
//                            color = Color.Black,
//                            fontWeight = FontWeight.Normal,
//                            fontFamily = Util.quicksand,
//                            style = TextStyle.Default,
//                            modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)
//                        )
//                    }
//                    Text(
//                        text = "Examples:",
//                        textAlign = TextAlign.Start,
//                        fontSize = 20.sp,
//                        color = Color.Black,
//                        fontWeight = FontWeight.Bold,
//                        fontFamily = Util.quicksand,
//                        style = TextStyle.Default,
//                        modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)
//                    )
//
//                    for (example in viewModel.singleLatestResponse.value.singleWordResponse.examples) {
//                        Text(
//                            text = "English: " + example.english.toString(),
//                            textAlign = TextAlign.Start,
//                            fontSize = 15.sp,
//                            color = Color.Black,
//                            fontWeight = FontWeight.Normal,
//                            fontFamily = Util.quicksand,
//                            style = TextStyle.Default,
//                            modifier = Modifier.padding(top = 2.dp, bottom = 2.dp)
//                        )
//                        Text(
//                            text = "Igbo: " + example.igbo.toString(),
//                            textAlign = TextAlign.Start,
//                            fontSize = 15.sp,
//                            color = Color.Black,
//                            fontWeight = FontWeight.Normal,
//                            fontFamily = Util.quicksand,
//                            style = TextStyle.Default,
//                            modifier = Modifier.padding(top = 2.dp, bottom = 2.dp)
//                        )
//
//                    }
//
//                    Text(
//                        text = "Related Words:",
//                        textAlign = TextAlign.Start,
//                        fontSize = 20.sp,
//                        color = Color.Black,
//                        fontWeight = FontWeight.Bold,
//                        fontFamily = Util.quicksand,
//                        style = TextStyle.Default,
//                        modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)
//                    )
//                    viewModel.singleLatestResponse.value.singleWordResponse.variations.forEach {
//
//                        Text(
//                            text = it,
//                            textAlign = TextAlign.Start,
//                            fontSize = 15.sp,
//                            color = Color.Black,
//                            fontWeight = FontWeight.Normal,
//                            fontFamily = Util.quicksand,
//                            style = TextStyle.Default,
//                            modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)
//                        )
////
//                    }
////            reponse.relatedTerms.forEach {
////                viewModel.getSingleWordMeaning(it)
////            }
//
//
////
////            reponse.stems.forEach {
////                Text(
////                    text = it,
////                    textAlign = TextAlign.Start,
////                    fontSize = 15.sp,
////                    color = Color.Black,
////                    fontWeight = FontWeight.Normal,
////                    fontFamily = Util.quicksand,
////                    style = TextStyle.Default,
////                    modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)
////                )
////            }
//
//                }
//            }
////            Row(horizontalArrangement = Arrangement.SpaceAround) {
////                CenterAlignedTopAppBar(
////                    navigationIcon = {
////                    IconButton(onClick = {
////                        scope.launch {
////                            sheetState.hide()
////                        }
////                    }) {
////                        Icon(Icons.Rounded.Close, contentDescription = "Cancel")
////                    }
////                    },
////                    title = {
//////                    Text("Search Result")
////                        Text(
////                            text = viewModel.singleLatestResponse.value.singleWordResponse.word.toString(),
////                            textAlign = TextAlign.Center,
////                            fontSize = 30.sp,
////                            color = Color.Black,
////                            fontWeight = FontWeight.Bold,
////                            fontFamily = Util.quicksand,
////                            style = TextStyle.Default,
////
////                            )
////                    },
////                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color.White)
////                )
////
////            }
//
//        }
//
//    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        Column() {

            if (showProcess) {
                LinearProgressIndicator(color = green, modifier = Modifier.fillMaxWidth())
            }

            Text(
                text = Util.getGreeting(),
                textAlign = TextAlign.Center,
                fontSize = 25.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontFamily = Util.quicksand,
                style = TextStyle.Default,
                modifier = Modifier.padding(start = 20.dp, top = 30.dp, bottom = 15.dp)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {

                TextField(
                    value = searchValue,
                    onValueChange = { text ->
                        searchValue = text

                    },
                    modifier = Modifier
                        .padding(
                            top = 0.dp, end = 20.dp, start = 20.dp, bottom = 10.dp
                        )
                        .fillMaxWidth()
                        .height(55.dp),
                    placeholder = {
                        Text(
                            text = "Search in Igbo or English",
                            textAlign = TextAlign.Center,
                            fontSize = 15.sp,
                            color = Color.Gray,
                            fontWeight = FontWeight.Bold,
                            fontFamily = Util.quicksand,
                            style = TextStyle.Default,

                            )
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(10.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        containerColor = grayLight,
                        cursorColor = green,
                       focusedTextColor = Color.Black

                    ),
                    textStyle = TextStyle.Default.copy(
                        fontFamily = Util.quicksand,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                    ),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                    keyboardActions = KeyboardActions(onSearch = {
                        keyController?.hide()

                        scope.launch {
                            viewModel.getWordMeanings(searchValue.text)
                            println(searchValue.text)
                            viewModel.addHistory(SearchHistory(null, searchValue.text))
                        }

                    })
                )
            }
            TabRow(selectedTabIndex = state, indicator = {
                TabRowDefaults.Indicator(
                    modifier = Modifier.tabIndicatorOffset(it[state]), color = green
                )
            }, containerColor = Color.White) {
                titles.forEachIndexed { index, title ->

                    Tab(selected = state == index, onClick = {
                        state = index
                        scope.launch {
                            pagerState.animateScrollToPage(index)
                        }

                    }, text = {
                        Text(
                            text = title,
                            textAlign = TextAlign.Center,
                            fontSize = 15.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            fontFamily = Util.quicksand,
                            style = TextStyle.Default,

                            )
                    }, selectedContentColor = green
                    )
                }

            }

            HorizontalPager(
                count = titles.size,
                state = pagerState,
                modifier = Modifier.fillMaxSize(),
                userScrollEnabled = false
            ) {

                when (it) {

                    0 -> {

                        Box(modifier = Modifier.fillMaxSize()) {

                            when (viewModel.latestResponse.value.status) {
                                Status.SUCCESS -> {

                                    if (viewModel.latestResponse.value.responce.isNotEmpty()) {

                                        LazyColumn() {
                                            items(viewModel.latestResponse.value.responce) {
                                                SearchResponseItem(it, viewModel) {
                                                    scope.launch {
                                                        sheetState.show()
                                                    }
                                                }

                                            }
                                        }
                                    } else {
                                        Box(
                                            modifier = Modifier.fillMaxSize(),
                                            contentAlignment = Alignment.Center
                                        ) {

                                            Column(
                                                verticalArrangement = Arrangement.Center,
                                                horizontalAlignment = Alignment.CenterHorizontally
                                            ) {

                                                Image(
                                                    painter = rememberAsyncImagePainter(model = R.drawable.undraw_empty),
                                                    contentDescription = null,
                                                    Modifier.size(250.dp)
                                                )

                                                Text(
                                                    text = "Noting found sorry",
                                                    textAlign = TextAlign.Center,
                                                    fontSize = 20.sp,
                                                    color = Color.Black,
                                                    fontWeight = FontWeight.Bold,
                                                    fontFamily = Util.quicksand,
                                                    style = TextStyle.Default,

                                                    )

                                            }


                                        }
                                    }
                                }
                                Status.NOTHING -> {

                                    Box(
                                        modifier = Modifier.fillMaxSize(),
                                        contentAlignment = Alignment.Center
                                    ) {

                                        Column(
                                            verticalArrangement = Arrangement.Center,
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {

                                            Image(
                                                painter = rememberAsyncImagePainter(model = R.drawable.undraw_search_png),
                                                contentDescription = null,
                                                Modifier.size(250.dp)
                                            )

                                            Text(
                                                text = "Type and click search on keyboard",
                                                textAlign = TextAlign.Center,
                                                fontSize = 20.sp,
                                                color = Color.Black,
                                                fontWeight = FontWeight.Bold,
                                                fontFamily = Util.quicksand,
                                                style = TextStyle.Default,

                                                )
                                        }

                                    }

                                }
                                Status.LOADING -> {
                                    Box(
                                        modifier = Modifier.fillMaxSize(),
                                        contentAlignment = Alignment.Center
                                    ) {

                                        Column(
                                            verticalArrangement = Arrangement.Center,
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {

                                            CircularProgressIndicator(color = green)


                                            Text(
                                                text = "Searching...",
                                                textAlign = TextAlign.Center,
                                                fontSize = 20.sp,
                                                color = Color.Black,
                                                fontWeight = FontWeight.Bold,
                                                fontFamily = Util.quicksand,
                                                style = TextStyle.Default,

                                                )
                                        }


                                    }

                                }
                                Status.FAILURE -> {
                                    Box(
                                        modifier = Modifier.fillMaxSize(),
                                        contentAlignment = Alignment.Center
                                    ) {

                                        Column(
                                            verticalArrangement = Arrangement.Center,
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {

                                            Image(
                                                painter = rememberAsyncImagePainter(model = R.drawable.undraw_empty),
                                                contentDescription = null,
                                                Modifier.size(250.dp)
                                            )

                                            Text(
                                                text = "Something went wrong",
                                                textAlign = TextAlign.Center,
                                                fontSize = 20.sp,
                                                color = Color.Black,
                                                fontWeight = FontWeight.Bold,
                                                fontFamily = Util.quicksand,
                                                style = TextStyle.Default,

                                                )
                                            Button(
                                                onClick = {
                                                    scope.launch {
                                                        viewModel.getWordMeanings(searchValue.text)
                                                        println(searchValue.text)
                                                    }
                                                },
                                                modifier = Modifier
                                                    .padding(
                                                        bottom = 70.dp,
                                                        top = 30.dp,
                                                        start = 30.dp,
                                                        end = 30.dp
                                                    )
                                                    .fillMaxWidth()
                                                    .height(60.dp),
                                                colors = ButtonDefaults.buttonColors(green)
                                            ) {

                                                Text(
                                                    text = "Try Again?",
                                                    fontWeight = FontWeight.Bold,
                                                    fontFamily = Util.quicksand,
                                                    color = Color.White
                                                )

                                            }
                                        }


                                    }

                                }
                            }
                        }

                    }
                    1 -> {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.White)
                        ) {

                            LazyColumn() {
                                items(viewModel.history) {
                                    SearchHistoryItem(searchHistory = it, onClick = {
                                        scope.launch {
                                            searchValue = TextFieldValue(it)
                                            pagerState.scrollToPage(0, 0f)
                                            state = 0
                                            viewModel.getWordMeanings(it)
                                            keyController?.hide()
                                        }
                                    }, onDelete = {
                                        viewModel.deleteHistory(it)
                                    })


                                }
                            }

                        }
                    }

                }

            }

        }
    }
}


