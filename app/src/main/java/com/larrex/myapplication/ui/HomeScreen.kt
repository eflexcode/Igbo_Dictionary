package com.larrex.myapplication.ui

import android.os.Handler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.larrex.myapplication.network.model.Responce
import com.larrex.myapplication.network.model.Status
import com.larrex.myapplication.ui.component.SearchResponseItem
import com.larrex.myapplication.ui.theme.green
import com.larrex.myapplication.ui.viewmodel.MainViewModel
import kotlinx.coroutines.launch

@OptIn(
    ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class,
    ExperimentalPagerApi::class
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

//    val wordMeaning by viewModel.getWordMeanings(searchValue.text)
//        .collectAsState(initial = Responce(Status.NOTHING, null))

    var state by remember { mutableStateOf(0) }

    if (sheetState.isVisible) {
        ModalBottomSheet(
            onDismissRequest = { /*TODO*/ },
            sheetState = sheetState,
            containerColor = Color.White, modifier = Modifier.height(600.dp)
        ) {

            Row(horizontalArrangement = Arrangement.SpaceAround) {
                CenterAlignedTopAppBar(
                    navigationIcon = {
//                    IconButton(onClick = {
//                        scope.launch {
//                            sheetState.hide()
//                        }
//                    }) {
//                        Icon(Icons.Rounded.Close, contentDescription = "Cancel")
//                    }
                    },
                    title = {
//                    Text("Search Result")
                        Text(
                            text = "Search Result",
                            textAlign = TextAlign.Center,
                            fontSize = 20.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            fontFamily = Util.quicksand,
                            style = TextStyle.Default,

                            )
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color.White)
                )

            }
            LazyColumn() {
                items(30) {
                    Text(text = "Hi my number is $it")
                }
            }
        }

    }
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
                    value = searchValue, onValueChange = { text ->
                        searchValue = text

                    }, modifier = Modifier
                        .padding(top = 0.dp, end = 20.dp, start = 20.dp, bottom = 10.dp)
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
                    }, singleLine = true,
                    shape = RoundedCornerShape(10.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                    ), textStyle = TextStyle.Default.copy(
                        fontFamily = Util.quicksand,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                    ), keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                    keyboardActions = KeyboardActions(onSearch = {
                        keyController?.hide()
//                        showProcess = true
//                        handler.postDelayed({
//                            showProcess = false
                        scope.launch {
                            viewModel.getWordMeanings(searchValue.text)
                            println(searchValue.text)
                        }
//                        }, 2000)

                    })
                )
            }
            TabRow(selectedTabIndex = state, indicator = {
                TabRowDefaults.Indicator(
                    modifier = Modifier.tabIndicatorOffset(it[state]),
                    color = green
                )
            }) {
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

//            HorizontalPager(count = 10) { page ->
//                // Our page content
//                Text(
//                    text = "Page: $page",
//                    modifier = Modifier.fillMaxSize()
//                )
//            }
            HorizontalPager(
                count = titles.size, state = pagerState, modifier = Modifier
                    .fillMaxSize(), userScrollEnabled = false
            ) {

                when (it) {

                    0 -> {

                        Box(modifier = Modifier.fillMaxSize()) {

                            when (viewModel.latestResponse.value.status) {
                                Status.SUCCESS -> {

                                    if (viewModel.latestResponse.value.responce.isNotEmpty()) {

                                        LazyColumn() {
                                            items(viewModel.latestResponse.value.responce) {
                                                SearchResponseItem(it,viewModel)

                                            }
                                        }
                                    }else{
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
                                                    contentDescription = null, Modifier.size(250.dp)
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
                                                contentDescription = null, Modifier.size(250.dp)
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
                                                contentDescription = null, Modifier.size(250.dp)
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
                                .background(Color.Black)
                        )
                    }

                }

            }

        }
    }
}


