package com.anto.posts.presentation.compossables.screens.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.anto.posts.R

@Composable
fun DetailsItem(
    text1: String,
    textValue: String
) {
    Card(
        modifier = Modifier
            .size(width = 110.dp, height = 110.dp)
            .padding(4.dp)
            .clip(RoundedCornerShape(15.dp))
            .border(
                width = 0.5.dp, color = colorResource(id = R.color.light_pink),
                shape = RoundedCornerShape(15.dp)
            ),
        elevation = 5.dp
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(Color(0xFF19D52AC), Color(0xFF3E2D8F)),
                        start = androidx.compose.ui.geometry.Offset(0f, 0f),
                        end = androidx.compose.ui.geometry.Offset(90f, -100f)
                    )
                )
        )

        Column(
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {

            Image(
                painter = painterResource(id = R.drawable.weather_star),
                contentDescription = null,
                modifier = Modifier
                    .height(25.dp)
                    .width(25.dp)
            )

            Text(
                text = text1,
                fontSize = 16.sp,
                color = Color.White
            )

            Text(
                text = textValue,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}
