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
import kotlinx.serialization.Serializable

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

@Serializable
internal sealed class PiggyBankNavigationItem(
    open val icon: Int = com.danipl.piggybank.R.drawable.ic_overview,
    open val tabName: String,
) {
    @Serializable
    data object Overview : PiggyBankNavigationItem(
        icon = com.danipl.piggybank.R.drawable.ic_overview,
        tabName = "Overview",
    )

    @Serializable
    data object Profile : PiggyBankNavigationItem(
        icon = com.danipl.piggybank.R.drawable.ic_profile,
        tabName = "Profile",
    )
}

private val navigationItems = listOf(
    PiggyBankNavigationItem.Overview,
    PiggyBankNavigationItem.Profile,
)
