package com.example.dz14_4400.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

@Composable
fun TabView() {
    var tabIndex by remember { mutableStateOf(0) }
    val tabs = listOf("Enertainment", "Trade", "It")
    Column(modifier = Modifier.fillMaxWidth()) {
        TabRow(selectedTabIndex = tabIndex) {
            tabs.forEachIndexed { index, title ->
                Tab(text = { Text(title) },
                    selected = tabIndex == index,
                    onClick = { tabIndex = index }
                )
            }
        }
        when (tabIndex) {
            0 -> {
                tabIndex = 0
                Text(text = "ENTERTAINMENT")
            }
            1 -> {
                tabIndex = 1
                Text(text = "TRADE")
            }
            2 -> {
                tabIndex = 2
                Text(text = "IT")
            }
        }
    }
}