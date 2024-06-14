package com.anto.weatherapp

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityInstrumentationTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testMainActivity() {
        // Start the activity
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        // Perform UI interactions and assertions using composeTestRule
        // For example, you can check if a specific composable is displayed
        composeTestRule.onNodeWithTag("bottom_navigation_bar").assertExists()

        // Don't forget to close the activity scenario
        activityScenario.close()
    }
}
