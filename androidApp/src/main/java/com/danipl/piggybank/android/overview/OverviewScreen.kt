package com.danipl.piggybank.android.overview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.danipl.piggybank.R
import com.danipl.piggybank.android.overview.components.PiggyBankListItem
import com.danipl.piggybank.android.theme.PiggyBankTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun OverviewRoute() {
    Scaffold(
        contentWindowInsets = ScaffoldDefaults.contentWindowInsets.exclude(NavigationBarDefaults.windowInsets),
        topBar = {
            CenterAlignedTopAppBar(
                title = { },
                actions = {},
                colors = topAppBarColors(
                    containerColor = PiggyBankTheme.colors.primaryContainer
                )
            )
        },
        modifier = Modifier.fillMaxSize(),
        ){ innerPadding ->
        OverviewScreen(
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
private fun OverviewScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = PiggyBankTheme.colors.primaryContainer
                ),
        ) {

            Text(
                text = stringResource(R.string.total_worth_amount, 25000),
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(Modifier.height(2.dp))

            Text(
                text = stringResource(R.string.total_worth_title)
            )

            Spacer(Modifier.height(50.dp))
        }



        assets.forEach { asset ->
            PiggyBankListItem(
                drawableRes = asset.imgDrawableRes,
                amount = asset.amount,
                title = asset.title
            )
        }

    }
}

data class Asset(
    val title: String,
    val amount: Double,
    val imgDrawableRes: Int
)

val assets = listOf<Asset>(
    Asset(
        title = "Personkonto",
        amount = 1000.00,
        imgDrawableRes = R.drawable.nordea
    ),
    Asset(
        title = "Sparkonto",
        amount = 20000.00,
        imgDrawableRes = R.drawable.nordea
    ),
    Asset(
        title = "Sparkonto",
        amount = 30000.00,
        imgDrawableRes = R.drawable.avanza
    ),
    Asset(
        title = "Savings account",
        amount = 25000.00,
        imgDrawableRes = R.drawable.revolut
    ),
    Asset(
        title = "Main account",
        amount = 4000.00,
        imgDrawableRes = R.drawable.revolut
    ),
    Asset(
        title = "Joint account",
        amount = 2000.00,
        imgDrawableRes = R.drawable.revolut
    ),
    Asset(
        title = "Personkonto",
        amount = 1000.00,
        imgDrawableRes = R.drawable.nordea
    ),
    Asset(
        title = "Sparkonto",
        amount = 20000.00,
        imgDrawableRes = R.drawable.nordea
    ),
    Asset(
        title = "Sparkonto",
        amount = 30000.00,
        imgDrawableRes = R.drawable.avanza
    ),
    Asset(
        title = "Savings account",
        amount = 25000.00,
        imgDrawableRes = R.drawable.revolut
    ),
    Asset(
        title = "Main account",
        amount = 4000.00,
        imgDrawableRes = R.drawable.revolut
    ),
    Asset(
        title = "Joint account",
        amount = 2000.00,
        imgDrawableRes = R.drawable.revolut
    ),
)