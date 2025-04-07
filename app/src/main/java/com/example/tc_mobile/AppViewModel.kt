package com.example.tc_mobile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel

class AppViewModel: ViewModel() {
    var first_name = ""
    var last_name = ""
    val number_picked = 0
}