package com.larrex.myapplication.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.larrex.myapplication.R
import com.larrex.myapplication.Util
import com.larrex.myapplication.room.model.SearchHistory

@Composable
fun SearchHistoryItem(searchHistory: SearchHistory,onClick: (keyword:String)-> Unit,onDelete: ()-> Unit) {

    Box(modifier = Modifier.fillMaxWidth().toggleable(true,true,onValueChange = {
        onClick(searchHistory.keyword)
    })) {

        Row(verticalAlignment = Alignment.CenterVertically) {

            Text(
                text = searchHistory.keyword,
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontFamily = Util.quicksand,
                style = TextStyle.Default,
                )

            IconButton(onClick = { onDelete() }) {

                Icon(painter = rememberAsyncImagePainter(model = R.drawable.ic_cross), contentDescription = null)

            }

        }

    }

}