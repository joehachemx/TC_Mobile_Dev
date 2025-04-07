package com.example.tc_mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tc_mobile.screens.MainScreen
import com.example.tc_mobile.screens.OnboardingScreen
import com.example.tc_mobile.screens.SpinnerView
import com.example.tc_mobile.ui.theme.TC_MOBILETheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TC_MOBILETheme {
                val navController = rememberNavController()
                val vm = AppViewModel()

                NavHost(navController = navController, startDestination = "main",
                    builder = {
                        composable("onboarding") {
                            OnboardingScreen(navController=navController, vm=vm)
                        }
                        composable("main") {
                            MainScreen(navController=navController, vm=vm)
                        }
                        composable("spinner") {
                            SpinnerView(navController=navController, vm=vm)
                        }
                    })
            }
        }
    }
}