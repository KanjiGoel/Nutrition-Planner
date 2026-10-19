package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.ui.screens.CreatePlanScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.ResultScreen
import com.example.ui.screens.SavedPlansScreen
import com.example.ui.theme.MyApplicationTheme

enum class AppScreen {
    HOME,
    CREATE_PLAN,
    RESULT,
    SAVED_PLANS
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    NutritionPlannerApp()
                }
            }
        }
    }
}

@Composable
fun NutritionPlannerApp() {
    var currentScreen by remember { mutableStateOf(AppScreen.HOME) }

    // Android System Back Navigation Handling
    BackHandler(enabled = currentScreen != AppScreen.HOME) {
        currentScreen = when (currentScreen) {
            AppScreen.CREATE_PLAN -> AppScreen.HOME
            AppScreen.RESULT -> AppScreen.CREATE_PLAN
            AppScreen.SAVED_PLANS -> AppScreen.HOME
            AppScreen.HOME -> AppScreen.HOME
        }
    }

    AnimatedContent(
        targetState = currentScreen,
        label = "ScreenTransition",
        transitionSpec = {
            if (targetState.ordinal > initialState.ordinal) {
                (slideInHorizontally { width -> width } + fadeIn()).togetherWith(
                    slideOutHorizontally { width -> -width } + fadeOut()
                )
            } else {
                (slideInHorizontally { width -> -width } + fadeIn()).togetherWith(
                    slideOutHorizontally { width -> width } + fadeOut()
                )
            }
        }
    ) { screen ->
        when (screen) {
            AppScreen.HOME -> {
                HomeScreen(
                    onCreatePlanClick = { currentScreen = AppScreen.CREATE_PLAN },
                    onSavedPlansClick = { currentScreen = AppScreen.SAVED_PLANS }
                )
            }
            AppScreen.CREATE_PLAN -> {
                CreatePlanScreen(
                    onBackClick = { currentScreen = AppScreen.HOME },
                    onGeneratePlanClick = { _, _, _, _, _, _, _, _, _ ->
                        currentScreen = AppScreen.RESULT
                    }
                )
            }
            AppScreen.RESULT -> {
                ResultScreen(
                    onBackClick = { currentScreen = AppScreen.CREATE_PLAN },
                    onSavePlanClick = { currentScreen = AppScreen.SAVED_PLANS }
                )
            }
            AppScreen.SAVED_PLANS -> {
                SavedPlansScreen(
                    onBackClick = { currentScreen = AppScreen.HOME },
                    onCreatePlanClick = { currentScreen = AppScreen.CREATE_PLAN },
                    onPlanSelect = { _ -> currentScreen = AppScreen.RESULT }
                )
            }
        }
    }
}
