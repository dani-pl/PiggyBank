package com.danipl.piggybank.android.editAsset

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.danipl.piggybank.android.components.PiggyBankAssetDetailItem
import com.danipl.piggybank.android.util.PreviewTheme

@Composable
fun EditAssetRoute(
    onNavigateBack: () -> Unit,
) {
    EditAssetScreen(
        onNavigateBack = onNavigateBack,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditAssetScreen(
    onNavigateBack: () -> Unit,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            painter = painterResource(id = com.danipl.piggybank.R.drawable.ic_back),
                            contentDescription = "",
                        )
                    }
                },
                actions = {
                    Row {
                        IconButton(onClick = { }) {
                            Icon(
                                painter = painterResource(id = com.danipl.piggybank.R.drawable.ic_close),
                                contentDescription = "",
                            )
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        IconButton(onClick = { }) {
                            Icon(
                                painter = painterResource(id = com.danipl.piggybank.R.drawable.ic_save),
                                contentDescription = "",
                            )
                        }
                    }
                },
            )
        },
    ) { innerPadding ->
        EditAssetContent(
            innerPadding = innerPadding,
        )
    }
}

@Composable
fun EditAssetContent(
    innerPadding: PaddingValues,
) {
    Column(
        modifier = Modifier.padding(innerPadding),
    ) {
        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            Image(
                modifier = Modifier.size(109.dp),
                painter = painterResource(id = com.danipl.piggybank.R.drawable.nordea),
                contentDescription = "",
            )
        }

        Spacer(modifier = Modifier.height(27.dp))

        PiggyBankAssetDetailItem(
            field = stringResource(id = com.danipl.piggybank.R.string.asset_name),
            content = "",
            readOnly = false,
        )
        PiggyBankAssetDetailItem(
            field = stringResource(id = com.danipl.piggybank.R.string.amount),
            content = "",
            readOnly = false,
        )
        PiggyBankAssetDetailItem(
            field = stringResource(id = com.danipl.piggybank.R.string.registered_on),
            content = "",
            readOnly = false,
        )
    }
}

@Preview
@Composable
private fun PreviewEditAssetScreen() {
    PreviewTheme {
        EditAssetScreen(
            onNavigateBack = {},
        )
    }
}
