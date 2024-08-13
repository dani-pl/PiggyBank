package com.danipl.piggybank.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.danipl.piggybank.android.overview.OverviewRoute
import com.danipl.piggybank.android.theme.PiggyBankTheme
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            PiggyBankTheme {
                Column(
                    verticalArrangement = Arrangement.Bottom,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = PiggyBankDestination.Overview,
                        modifier = Modifier.weight(1f)
                    ) {
                        composable<PiggyBankDestination.Overview> {
                            OverviewRoute()
                        }
                        composable<PiggyBankDestination.Profile> {

                        }
                    }
                    PiggyBankBottomNavigationBar()
                }
            }
        }
    }
}

@Serializable
internal sealed class PiggyBankDestination(
    open val icon: Int,
    open val tabName: String
){
    @Serializable
    data object Overview: PiggyBankDestination(
        icon = com.danipl.piggybank.R.drawable.ic_overview,
        tabName = "Overview"
    )
    @Serializable
    data object Profile: PiggyBankDestination(
        icon = com.danipl.piggybank.R.drawable.ic_profile,
        tabName = "Profile"
    )
}
