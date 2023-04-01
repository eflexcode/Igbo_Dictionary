package com.larrex.myapplication.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.larrex.myapplication.R
import com.larrex.myapplication.ui.theme.green
import com.larrex.myapplication.ui.theme.greenLight

@Composable
fun PronunciationItem(play: () -> Unit, isPlaying: Boolean) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(greenLight), contentAlignment = Alignment.Center
    ) {

        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
            IconButton(
                onClick = {


                },
                modifier = Modifier.size(30.dp).padding(start = 5.dp)
            ) {
                Icon(
                    painter = painterResource(id = if (isPlaying) R.drawable.ic_round_pause else R.drawable.ic_round_play),
                    contentDescription = null, modifier = Modifier.size(30.dp), tint = green
                )
            }
            Slider(
                value = 50F,
                onValueChange = {


                },
                modifier = Modifier
                    .padding(start = 5.dp, end = 5.dp),
                valueRange = 1f..100f,
                colors = SliderDefaults.colors(
                    thumbColor = green,
                    activeTickColor = green
                )
            )
        }


    }

}