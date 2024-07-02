package com.anto.posts.presentation.compossables.screens.alerts

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.anto.posts.R
import com.anto.weatherapp.data.models.Alert
import com.anto.posts.presentation.compossables.screens.home.HomeViewModel
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun AlertScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {

    val state = viewModel.state.value

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.app_background),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {


        LazyColumn(
            contentPadding = PaddingValues(8.dp),
            content = {
                val alerts: List<Alert> = state.data?.alerts?.alert ?: emptyList()
                items(alerts) { details ->
                    AlertItem(
                        severity = details.severity!!,
                        headline = details.headline!!
                    )
                }
            }
        )
    }
}