package com.larrex.myapplication.ui

import android.os.Handler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.*
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
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.larrex.myapplication.R
import com.larrex.myapplication.Util
import com.larrex.myapplication.ui.theme.green
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun HomeScreen() {
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

    if (sheetState.isVisible) {
        ModalBottomSheet(
            onDismissRequest = { /*TODO*/ },
            sheetState = sheetState,
            containerColor = Color.White,modifier = Modifier.height(600.dp)
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

        LazyColumn() {
            item {

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

//
//                    Icon(
//                        painter = rememberAsyncImagePainter(model = R.drawable.ic_filter),
//                        contentDescription = null, modifier = Modifier.padding(start = 15.dp)
//                            .size(20.dp).toggleable(true,true, onValueChange = {
//
//                            })
//                    )

                    TextField(
                        value = searchValue, onValueChange = { text ->
                            searchValue = text

                        }, modifier = Modifier
                            .padding(top = 0.dp, end = 20.dp, start = 20.dp, bottom = 0.dp)
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
                            showProcess=true
                            handler.postDelayed({
                                showProcess=false
                                scope.launch {
                                    sheetState.show()
                                }
                            }, 2000)
                        })
                    )
                }
//                Button(
//                    onClick = {
//                        scope.launch {
//                            sheetState.show()
//                        }
//                    },
//                    modifier = Modifier
//                        .padding(bottom = 70.dp, top = 30.dp, start = 30.dp, end = 30.dp)
//                        .fillMaxWidth()
//                        .height(60.dp),
//                    colors = ButtonDefaults.buttonColors(green)
//                ) {
//
//                    Text(
//                        text = "see bottom sheet",
//                        fontWeight = FontWeight.Bold,
//                        fontFamily = Util.quicksand,
//                        color = Color.White
//                    )

            }
        }
    }

}


