package com.larrex.myapplication.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.larrex.myapplication.Util
import com.larrex.myapplication.ui.theme.green
import com.larrex.myapplication.ui.theme.greenLight


@Composable
fun ProviderChip(
    chipText: String,
    chipSelected: Boolean = false,
    onChipSelected: (String) -> Unit,
    modifier: Modifier =
        Modifier
            .padding(2.dp)
            .clip(RoundedCornerShape(17.dp))
) {

    Surface(
        modifier = modifier,
        color = if (chipSelected) Color.White else greenLight,

        ) {

        Row(modifier = Modifier
            .toggleable(value = chipSelected, onValueChange = {
                onChipSelected(chipText)
            })
            .padding(top = 7.dp, end = 6.dp, start = 6.dp, bottom = 7.dp)) {

            Text(
                text = chipText,
                fontSize = 11.sp,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold,
                color = if (chipSelected) Color.White else Color.White,
                fontFamily = Util.quicksand
            )

        }

    }

}