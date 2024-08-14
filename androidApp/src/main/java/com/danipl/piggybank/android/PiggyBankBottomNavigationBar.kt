package com.danipl.piggybank.android

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource

@Composable
fun PiggyBankBottomNavigationBar() {
    var selectedItem by remember { mutableIntStateOf(0) }

    NavigationBar(
        containerColor = Color.Transparent,
    ) {
        navigationItems.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = { Icon(painter = painterResource(item.icon), contentDescription = item.tabName) },
                label = { Text(item.tabName) },
                selected = selectedItem == index,
                onClick = { selectedItem = index },
            )
        }
    }
}

private val navigationItems = listOf(
    PiggyBankDestination.Overview,
    PiggyBankDestination.Profile,
)
