package com.larrex.myapplication.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.larrex.myapplication.R
import com.larrex.myapplication.Util
import com.larrex.myapplication.room.model.SearchHistory

@Composable
fun SearchHistoryItem(searchHistory: SearchHistory,onClick: (keyword:String)-> Unit,onDelete: (id:Int)-> Unit) {

    Box(modifier = Modifier
        .fillMaxWidth()
        .toggleable(true, true, onValueChange = {
            onClick(searchHistory.keyword)
        })) {

        Row(verticalAlignment = Alignment.CenterVertically, ) {

            Text(
                text = searchHistory.keyword,
                textAlign = TextAlign.Start,
                fontSize = 14.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontFamily = Util.quicksand,
                style = TextStyle.Default,
                modifier = Modifier
                    .weight(2f)
                    .padding(start = 10.dp)
                )

            IconButton(modifier = Modifier.weight(0.3f),onClick = { searchHistory.id?.let {
                onDelete(
                    it
                )
            } }, colors = IconButtonDefaults.iconButtonColors(contentColor = Color.Black)) {

                Icon(painter = rememberAsyncImagePainter(model = R.drawable.ic_cross), contentDescription = null)

            }

        }

    }

}