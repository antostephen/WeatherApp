package com.anto.weatherapp.presentation.compossables.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.anto.weatherapp.R

@Composable
fun DailyItem(
    day: String,
    degrees: Float,
    icon: String
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color(0xFF19D52AC), Color(0xFF3E2D8F)),
                    start = androidx.compose.ui.geometry.Offset(50f, 100f),
                    end = androidx.compose.ui.geometry.Offset(90f, 0f)
                )
            )
    )
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .border(
                width = 0.5.dp, color = colorResource(id = R.color.light_pink),
                shape = RoundedCornerShape(15.dp)
            ),
        shape = RoundedCornerShape(8.dp),
        backgroundColor = Color.Transparent,
        elevation = 5.dp
    )

    {
        Row(
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Text(
                modifier = Modifier.align(CenterVertically),
                text = day,
                fontSize = 16.sp,
                color = Color.LightGray,
                textAlign = TextAlign.Start
            )
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(icon)
                    .crossfade(true)
                    .build(),
                contentDescription = "Daily forecast $day,",
                modifier = Modifier
                    .size(60.dp),
                alignment = Alignment.Center
            )
            Text(
                modifier = Modifier.align(CenterVertically),
                text = "${degrees}${0x00B0.toChar()}c",
                fontSize = 16.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }
}