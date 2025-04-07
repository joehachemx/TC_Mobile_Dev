package com.example.tc_mobile.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.tc_mobile.AppViewModel
import com.example.tc_mobile.components.CustomButton
import com.example.tc_mobile.components.CustomTextField

@Composable
fun OnboardingScreen(navController: NavController, vm: AppViewModel) {
    var first_name by remember { mutableStateOf("") }
    var last_name by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            "Onboarding Screen",
            modifier = Modifier.padding(8.dp).padding(top = 20.dp),
            fontSize = 20.sp,
        )

        CustomTextField(label="Please insert your name", value = first_name, onValueChange = { first_name = it })
        CustomTextField(label="Please insert your age", value = last_name, onValueChange = { last_name = it })

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.padding(top = 20.dp, bottom = 20.dp)
        ) {
            CustomButton("Submit") {
                navController.navigate("main")
            }
        }
    }
}