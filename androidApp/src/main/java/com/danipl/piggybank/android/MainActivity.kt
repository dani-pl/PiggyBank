package com.danipl.piggybank.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.danipl.piggybank.android.editAsset.EditAssetRoute
import com.danipl.piggybank.android.overview.OverviewRoute
import com.danipl.piggybank.android.theme.PiggyBankTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.Serializable

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val mainViewModel = viewModel<MainViewModel>()
            val state: MainState by mainViewModel.state.collectAsState()
            val navController = rememberNavController()

            PiggyBankTheme {
                Column(
                    verticalArrangement = Arrangement.Bottom,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background),
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = PiggyBankDestination.Overview,
                        modifier = Modifier.weight(1f),
                    ) {
                        composable<PiggyBankDestination.Overview> {
                            mainViewModel.setBottomNavigationBarVisibility(true)
                            OverviewRoute(
                                onFabClicked = { navController.navigate(PiggyBankDestination.EditAsset) },
                            )
                        }
                        composable<PiggyBankDestination.Profile> {
                            mainViewModel.setBottomNavigationBarVisibility(true)
                        }
                        composable<PiggyBankDestination.EditAsset> {
                            mainViewModel.setBottomNavigationBarVisibility(false)
                            EditAssetRoute(
                                onNavigateBack = { navController.popBackStack() },
                            )
                        }
                    }
                    AnimatedVisibility(
                        visible = state.isBottomNavigationBarVisible,
                    ) {
                        PiggyBankBottomNavigationBar()
                    }
                }
            }
        }
    }
}

@Serializable
sealed class PiggyBankDestination {

    @Serializable
    data object Overview

    @Serializable
    data object Profile

    @Serializable
    data object EditAsset
}
