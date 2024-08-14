package com.danipl.piggybank.android.overview.components

import androidx.compose.foundation.Image
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.danipl.piggybank.R

@Composable
fun PiggyBankListItem(
    drawableRes: Int = R.drawable.nordea,
    title: String,
    amount: Double
) {
    ListItem(
        headlineContent = { Text(title) },
        leadingContent = {
            Image(
                painter = painterResource(drawableRes),
                contentDescription = ""
            )
        },
        trailingContent = {
            Text(
                text = stringResource(id = R.string.overview_screen_list_item_amount_kr, amount),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    )
}