package com.anto.posts.presentation.compossables.screens.home

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.anto.posts.R
import com.anto.posts.data.models.Forecastday
import com.anto.posts.domain.entities.Locations
import com.anto.posts.presentation.compossables.screens.home.components.DailyItem
import com.anto.posts.presentation.compossables.screens.home.components.DetailsItem
import com.anto.core.ui.theme.Blue
import com.anto.core.ui.theme.Gray
import com.anto.core.utils.dayOfTheWeek
import com.hitanshudhawan.circularprogressbar.CircularProgressBar
import com.ramcosta.composedestinations.annotation.Destination
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Destination(start = true)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    val context = LocalContext.current
    val locationDialog = remember { mutableStateOf(false) }
    val localLocations: List<Locations> =
        viewModel.allLocations.observeAsState().value ?: emptyList()

    if (state.isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressBar(
                modifier = Modifier.size(dimensionResource(R.dimen.padding_120dp)),
                progress = 30f,
                progressMax = 100f,
                progressBarColor = Color.Blue,
                progressBarWidth = dimensionResource(R.dimen.padding_20dp),
                backgroundProgressBarColor = Color.Gray,
                backgroundProgressBarWidth = dimensionResource(R.dimen.padding_10dp),
                roundBorder = true,
                startAngle = 90f
            )
        }
    }

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

    //Added Locations
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            LazyRow(
                contentPadding = PaddingValues(end = dimensionResource(R.dimen.padding_8dp)),
                content = {
                    items(localLocations) { location ->

                        Card(
                            modifier = Modifier
                                .padding(start = dimensionResource(R.dimen.padding_4dp))
                                .height(dimensionResource(R.dimen.padding_30dp))
                                .clickable {
                                    viewModel.saveToSharedPrefs(location.locationName)
                                    Toast
                                        .makeText(
                                            context, "${location.locationName} set as Default",
                                            Toast.LENGTH_LONG
                                        )
                                        .show()
                                },
                            backgroundColor = if (location.locationName == viewModel.currentLocation.value) {
                                Blue
                            } else Gray,
                            elevation = dimensionResource(R.dimen.padding_5dp),
                            shape = RoundedCornerShape(dimensionResource(R.dimen.padding_8dp))
                        ) {
                            Row(
                                modifier = Modifier
                                    .padding(start = dimensionResource(R.dimen.padding_8dp), end = dimensionResource(R.dimen.padding_8dp)),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = location.locationName,
                                    color = Color.White
                                )
                                IconButton(onClick = {
                                    GlobalScope.launch(Dispatchers.Main) {
                                        viewModel.deleteLocation(Locations(locationName = location.locationName))
                                    }
                                    Toast.makeText(
                                        context, "${location.locationName} Deleted..",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }) {

                                    Icon(

                                        imageVector = Icons.Default.Clear,
                                        tint = Color.White,
                                        contentDescription = null
                                    )
                                }
                            }
                        }

                    }
                }
            )

            //Add Button
            Card(
                modifier = Modifier
                    .padding(start = dimensionResource(R.dimen.padding_8dp))
                    .height(dimensionResource(R.dimen.padding_30dp))
                    .clickable {
                        locationDialog.value = true
                    }
                    .background(Blue)
                    .width(dimensionResource(R.dimen.padding_30dp))
            ) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = stringResource(id = R.string.add_city),
                    modifier = Modifier.size(ButtonDefaults.IconSize)
                )
            }
        }

        //Display Location : City, Country
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.padding_15dp)),
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                modifier = Modifier.size(dimensionResource(R.dimen.padding_35dp)),
                imageVector = Icons.Outlined.LocationOn,
                contentDescription = null,
                tint = Color.White
            )
            Spacer(modifier = Modifier.width(dimensionResource(R.dimen.padding_8dp)))

            Text(
                text = "${state.data?.location?.name}, ${state.data?.location?.country} ",
                color = Color.White,
                fontSize = 25.sp
            )

        }

        Column(
            modifier = Modifier
                .padding(end = dimensionResource(R.dimen.padding_16dp)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data("https:${state.data?.current?.condition?.icon}")
                    .crossfade(true)
                    .build(),
                contentDescription = "${state.data?.current?.condition?.text}",
                modifier = Modifier
                    .size(dimensionResource(R.dimen.padding_140dp))
            )

            Column(
                modifier = Modifier.padding(start = dimensionResource(R.dimen.padding_8dp)),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "${state.data?.current?.tempC}${0x00B0.toChar()}c",
                    style = MaterialTheme.typography.h2.merge(),
                    color = Color.White,
                    modifier = Modifier
                        .padding(
                            start = dimensionResource(R.dimen.padding_18dp),
                            end = dimensionResource(R.dimen.padding_18dp),
                        )
                )
                Text(
                    text = "${state.data?.current?.condition?.text}",
                    style = MaterialTheme.typography.body1.merge(),
                    color = Color.White,
                    modifier = Modifier
                        .padding(
                            start = dimensionResource(R.dimen.padding_18dp),
                            bottom =  dimensionResource(R.dimen.padding_8dp)
                        )
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.house_new),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(dimensionResource(R.dimen.padding_220dp))
                        .width(dimensionResource(R.dimen.padding_220dp))
                )
            }

        }

        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_8dp)))

        //Weather details
        Column(
            modifier = Modifier
                .clip(
                    shape = RoundedCornerShape(dimensionResource(R.dimen.padding_10dp))
                )
                .background(Color.Transparent)
        ) {


            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                DetailsItem(
                    text1 = stringResource(R.string.feels_like),
                    textValue = "${state.data?.current?.feelslikeC}${0x00B0.toChar()}"
                )
                Spacer(modifier = Modifier.width(dimensionResource(R.dimen.padding_8dp)))
                DetailsItem(
                    text1 = stringResource(R.string.feels_like),
                    textValue = "${state.data?.current?.windKph} kp/h"
                )
                Spacer(modifier = Modifier.width(dimensionResource(R.dimen.padding_8dp)))
                DetailsItem(
                    text1 = stringResource(R.string.pressure),
                    textValue = "${state.data?.current?.pressureMb} Mb"
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.padding_4dp)),
                horizontalArrangement = Arrangement.Center
            ) {
                DetailsItem(
                    text1 = stringResource(R.string.humidity),
                    textValue = "${state.data?.current?.humidity}%"
                )
                Spacer(modifier = Modifier.width(dimensionResource(R.dimen.padding_8dp)))
                DetailsItem(
                    text1 = stringResource(R.string.wind_direction),
                    textValue = "${state.data?.current?.windDir}"
                )
                Spacer(modifier = Modifier.width(dimensionResource(R.dimen.padding_8dp)))
                DetailsItem(
                    text1 = stringResource(R.string.uv_index),
                    textValue = "${state.data?.current?.uv}"
                )

            }
        }

        //Hourly Weather
        LazyRow(
            contentPadding = PaddingValues(horizontal = dimensionResource(R.dimen.padding_8dp)),
            content = {
                val hourForecast: List<Forecastday> =
                    state.data?.forecast?.forecastday ?: emptyList()
                items(hourForecast) {
                    it.hour.forEach { hour ->
                        HourItem(
                            icon = "https:${hour.condition?.icon}",
                            degrees = hour.tempC?.toFloat() ?: 0F,
                            time = hour.time!!,
                        )
                    }

                }
            }
        )

        //Next 3 days prediction
        Column(
            modifier = Modifier
                .size(height = dimensionResource(R.dimen.padding_250dp), width = dimensionResource(R.dimen.padding_480dp))
                .padding(start = dimensionResource(R.dimen.padding_16dp)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyColumn(
                contentPadding = PaddingValues(vertical = dimensionResource(R.dimen.padding_2dp)),
                content = {
                    val dailyForecast: List<Forecastday> =
                        state.data?.forecast?.forecastday ?: emptyList()
                    items(dailyForecast) { details ->
                        details.day.avgtempC?.toFloat()?.let { it1 ->
                            DailyItem(
                                day = dayOfTheWeek(details.date!!),
                                degrees = it1,
                                icon = "https:${details.day.condition?.icon}"
                            )
                        }
                    }
                })
        }

        if (locationDialog.value) {
            AlertDialog(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.padding_8dp)),
                onDismissRequest = { locationDialog.value = false },
                title = {
                    Text(
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold
                        ),
                        text = stringResource(R.string.add_location),
                        modifier = Modifier.padding(dimensionResource(R.dimen.padding_8dp)),
                    )
                },
                text = {
                    TextField(
                        value = viewModel.locationDialogValue.value,
                        onValueChange = {
                            viewModel.setLocationDialogValue(it)
                        },
                        textStyle = TextStyle(color = Color.White),
                        placeholder = { Text(text = stringResource(R.string.bengaluru), color = Color.LightGray) },
                    )
                },
                confirmButton = {
                    Button(
                        colors = ButtonDefaults.buttonColors(Blue),
                        onClick = {
                            viewModel.addLocation()
                            locationDialog.value = false
                            Toast.makeText(context, "Locations added", Toast.LENGTH_LONG).show()
                        }
                    ) {
                        Text(text = "Add", color = Color.White)
                    }
                },
                backgroundColor = Color.Black,
                contentColor = Color.White,
                shape = RoundedCornerShape(dimensionResource(R.dimen.padding_12dp))
            )

        }
    }
}


