package com.larrex.myapplication.ui

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.larrex.myapplication.R
import com.larrex.myapplication.Util
import com.larrex.myapplication.ui.navigation.NavScreens
import com.larrex.myapplication.ui.theme.green

@Composable
fun FirstTimeScreen(navController: NavController) {
    val systemUiController = rememberSystemUiController()
//    systemUiController.isStatusBarVisible = false
//    systemUiController.isNavigationBarVisible = false
//    systemUiController.isSystemBarsVisible = false
    systemUiController.setSystemBarsColor(Color.Transparent,true)
    val colors = listOf<Color>(Color.Transparent, Color.Black)
    val context = LocalContext.current

    val sharedPreferences = context.getSharedPreferences("first", Context.MODE_PRIVATE)
    sharedPreferences.edit().putString("firstRun", "yes").commit()

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        Image(
            painter = rememberAsyncImagePainter(model = R.drawable.igbo_girl_dancing),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .drawWithCache {
                    val colorList = Brush.verticalGradient(colors)
                    onDrawWithContent {
                        drawContent()
                        drawRect(colorList, blendMode = BlendMode.Multiply)
                    }
                },
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center
        )

        Column() {
            Text(
                text = "Igbo Dictionary",
                textAlign = TextAlign.Center,
                fontSize = 60.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontFamily = Util.quicksand,
                style = TextStyle.Default

            )

            Text(
                text = "Igbo is spoken in southern Nigeria, Kogi, Benue, Equatorial Guinea, Cameroon, Haiti,Barbados, Belize, Trinidad and Tobago, it also supplied a large chunk of words to the Jamaican Patois.",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 40.dp, start = 40.dp, top = 20.dp),
                textAlign = TextAlign.Center,
                fontSize = 14.sp,
                color = Color.White,
                fontWeight = FontWeight.Medium,
                fontFamily = Util.quicksand

            )
        }

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter){
            Button(
                onClick = {
                    sharedPreferences.edit().putBoolean("firstrun", false).commit()

                    navController.navigate(NavScreens.Home.route){
                        popUpTo(0) {
                        }
                    }

                },
                modifier = Modifier
                    .padding(bottom = 70.dp, top = 30.dp, start = 30.dp, end = 30.dp)
                    .fillMaxWidth()
                    .height(60.dp),
                colors = ButtonDefaults.buttonColors(green)
            ) {

                Text(
                    text = "Continue To App",
                    fontWeight = FontWeight.Bold,
                    fontFamily = Util.quicksand,
                    color = Color.White
                )

            }
        }

    }
}