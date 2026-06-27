package com.anto.posts.presentation.compossables.bottomnavigation

import com.anto.posts.R
import com.anto.posts.presentation.compossables.screens.destinations.*

sealed class BottomNavigationItem(var title: String, var icon: Int,var destination: Destination) {
    object Home : BottomNavigationItem("Home", R.drawable.ic_baseline_home_24,HomeScreenDestination)
    object Search : BottomNavigationItem("Search", R.drawable.ic_baseline_search_24,SearchScreenDestination)
    object Alert : BottomNavigationItem("Alerts", R.drawable.ic_alerts,AlertScreenDestination)
}
