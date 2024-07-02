package com.anto.posts.presentation.compossables.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ramcosta.composedestinations.annotation.Destination
import timber.log.Timber

@Destination
@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel()
) {

    val state = viewModel.state.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        SearchBar(
            viewModel = viewModel,
            modifier = Modifier
                .fillMaxWidth()
                .height(67.dp)
                .padding(8.dp),
            onSearch = {
                viewModel.search(it)
            }
        )


        Timber.i("Results: ${state.data}")

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(85.dp),
            shape = RoundedCornerShape(12.dp),
            backgroundColor = Color.Transparent,
            elevation = 15.dp
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
                // .padding(16.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                state.data?.location?.let {
                    Text(
                        text = it.name,
                        fontSize = 20.sp,
                        color = Color.White,
                        modifier = Modifier.padding(start = 8.dp, end = 8.dp)
                    )
                }

                Column(

                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data("https:${state.data?.current?.condition?.icon}")
                                .crossfade(true)
                                .build(),
                            contentDescription = "${state.data?.current?.condition?.text}",
                            modifier = Modifier
                                .size(60.dp)
                        )

                        Text(
                            text = "${state.data?.current?.tempC}${0x00B0.toChar()}",
                            style = MaterialTheme.typography.h6.merge(),
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(
                                    start = 18.dp,
                                    end = 18.dp,
                                ),

                            )
                    }

                }
            }
        }

    }
}

@Composable
fun SearchBar(
    viewModel: SearchViewModel,
    modifier: Modifier = Modifier,
    onSearch: (String) -> Unit = {}
) {
    val query = viewModel.query.value

    TextField(
        value = query,
        onValueChange = {
            viewModel.setSearchQuery(it)
        },
        placeholder = {
            Text(
                text = "Enter city...",
                color = Color.LightGray
            )
        },
        modifier = modifier
            .fillMaxWidth()
            .shadow(4.dp, CircleShape)
            .background(Color.Transparent, CircleShape),
        shape = MaterialTheme.shapes.medium,
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.Words,
            autoCorrect = true,
            keyboardType = KeyboardType.Text,
        ),
        textStyle = TextStyle(color = Color.White),
        maxLines = 1,
        singleLine = true,
        trailingIcon = {
            IconButton(onClick = { onSearch(query) }) {
                Icon(
                    imageVector = Icons.Default.Search,
                    tint = Color.LightGray,
                    contentDescription = null
                )
            }
        }
    )
}