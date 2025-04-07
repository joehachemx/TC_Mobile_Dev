package com.example.tc_mobile.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerLayoutType
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.example.tc_mobile.AppViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Calendar
import kotlin.random.Random

// based on a clock view, 12 tries

@Composable
fun SpinnerView(navController: NavController, vm: AppViewModel) {
    Column(
        modifier = Modifier.fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            "Spin!!",
            modifier = Modifier.fillMaxWidth().padding(8.dp).padding(top = 20.dp).background(color = Color.Black).zIndex(2f).padding(top = 120.dp, bottom = 120.dp),
            fontSize = 20.sp,
            color = Color.White,
            textAlign = TextAlign.Center
        )

        IntegerPickerAutomatic(
            onConfirm = {},
            onDismiss = {},
            navController = navController,
            winningNumber = vm.number_picked
        )

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IntegerPickerAutomatic(
    onConfirm: (Int) -> Unit,
    onDismiss: () -> Unit,
    navController: NavController,
    winningNumber: Int
) {
    fun getRandomInt(min: Int = 0, max: Int = 24): Int { // Adjusted max for hours
        require(min < max) { "Min must be less than max" }
        return Random.nextInt(min, max)
    }

    val currentTime = Calendar.getInstance()
    val initialHour = currentTime.get(Calendar.HOUR_OF_DAY)
    val timePickerState = rememberTimePickerState(
        initialHour = initialHour,
    )

    val coroutineScope = rememberCoroutineScope()
    var resultText by remember { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .offset(y = -150.dp)
                .padding(top = 10.dp)
        ) {
            TimePicker(
                state = timePickerState,
                modifier = Modifier.fillMaxWidth(),
                layoutType = TimePickerLayoutType.Vertical
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            coroutineScope.launch {
                var finalHour = 0 // Store the final hour after spinning
                for (i in 1..15) {
                    val newHour = getRandomInt()
                    timePickerState.hour = newHour  // Update TimePicker state
                    finalHour = newHour

                    delay(100) // Short delay for visual effect
                }

                // Compare with winning number after spinning
                resultText = if (finalHour == winningNumber) {
                    "YOU WIN"
                } else {
                    "RETRYY"
                }

                //After "spinning", navigate
                navController.navigate("main") //Moved this to trigger after pressing the new button
            }
        }) {
            Text("Spin")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(resultText) // Display result

        if (resultText.isNotEmpty()) {
            Button(onClick = { navController.navigate("main") }) {
                Text("Go to Main")
            }
        }
    }
}