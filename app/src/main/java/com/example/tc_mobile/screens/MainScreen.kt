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
import java.util.Calendar

@Composable
fun MainScreen(navController: NavController, vm: AppViewModel) {
    var selectedNumber by remember { mutableStateOf<Int?>(null) }

    Column(
        modifier = Modifier.fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            "Welcome, ${vm.first_name} ${vm.last_name}",
            modifier = Modifier.fillMaxWidth().padding(8.dp).padding(top = 20.dp).background(color = Color.Black).zIndex(2f).padding(top = 120.dp, bottom = 120.dp),
            fontSize = 20.sp,
            color = Color.White,
            textAlign = TextAlign.Center
        )

        IntegerPicker(
            onConfirm = { selected ->
                selectedNumber = selected  // Store the selected number
            },
            onDismiss = {}
        )

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IntegerPicker(
    onConfirm: (Int) -> Unit,
    onDismiss: () -> Unit
) {
    val currentTime = Calendar.getInstance()

    val timePickerState = rememberTimePickerState(
        initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
    )

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

        Button(onClick = { onConfirm(timePickerState.hour) }) {
            Text("Confirm selection")
        }
    }
}