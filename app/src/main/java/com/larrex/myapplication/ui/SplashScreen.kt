package com.larrex.myapplication.ui

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Handler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.larrex.myapplication.R
import com.larrex.myapplication.Util
import com.larrex.myapplication.ui.navigation.NavScreens

@Composable
fun SplashScreen(navController: NavController) {

    val systemUiController = rememberSystemUiController()
//    systemUiController.isStatusBarVisible = false
//    systemUiController.isNavigationBarVisible = false
//    systemUiController.isSystemBarsVisible = false
    systemUiController.setSystemBarsColor(Color.Transparent, true)
    val colors = listOf<Color>(Color.Transparent, Color.Black)

    val context = LocalContext.current
    val handler = Handler()

    val sharedPreferences = context.getSharedPreferences("first", MODE_PRIVATE)

    val first = sharedPreferences.getString("firstRun", "yes")

    handler.postDelayed({
        if (sharedPreferences.getBoolean("firstrun", true)) {

            navController.navigate(NavScreens.FirstTime.route) {
                popUpTo(0) {
                }
            }
        } else {
            navController.navigate(NavScreens.Home.route) {
                popUpTo(0) {
                }
            }
        }
    }, 2000)


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

        Text(
            text = "Igbo Dictionary",
            textAlign = TextAlign.Center,
            fontSize = 60.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontFamily = Util.quicksand,
            style = TextStyle.Default

        )

    }

}