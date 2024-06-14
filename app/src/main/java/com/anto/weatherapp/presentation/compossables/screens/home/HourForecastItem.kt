package com.anto.weatherapp.presentation.compossables.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.anto.weatherapp.R
import com.anto.weatherapp.utils.formatTime

@Composable
fun HourItem(
    icon: String,
    time: String,
    degrees: Float
) {
    Card(
        modifier = Modifier
            .size(width = 80.dp, height = 150.dp)
            .padding(5.dp)
            .border(
                width = 0.5.dp, color = colorResource(id = R.color.light_pink),
                shape = RoundedCornerShape(55.dp)
            ),
        shape = RoundedCornerShape(20.dp),
        backgroundColor = Color.Transparent,
        elevation = 5.dp
    ) {
        Box(
            modifier = Modifier
                .size(width = 90.dp, height = 140.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.weather_tile_in_active),
                contentDescription = null,
                modifier = Modifier.fillMaxSize()

            )
        }

        Column(
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = formatTime(time),
                fontSize = 12.5.sp,
                modifier = Modifier.padding(top = 20.dp),
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center
            )
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(icon)
                    .crossfade(true)
                    .build(),
                contentDescription = "hour forecast $time,",
                modifier = Modifier
                    .size(50.dp)
                    .align(Alignment.CenterHorizontally),
                alignment = Alignment.Center
            )
            Text(
                text = "${degrees}${0x00B0.toChar()}c",
                fontSize = 13.sp,
                modifier = Modifier.padding(bottom = 20.dp),
                color = Color.White,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
            )
        }
    }
}