package com.danipl.piggybank.android.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.danipl.piggybank.android.util.PreviewTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@Composable
fun PiggyBankAssetDetailItem(
    field: String,
    content: String,
    readOnly: Boolean,
) {
    val _textFieldValue = MutableStateFlow(content)
    val textFieldValue: String by _textFieldValue.asStateFlow().collectAsState()

    ListItem(
        headlineContent = {},
        leadingContent = {
            Row(
                verticalAlignment = Alignment.Bottom,
            ) {
                Text(field)
            }
        },
        trailingContent = {
            TextField(
                readOnly = readOnly,
                value = textFieldValue,
                onValueChange = { newValue ->
                    _textFieldValue.update {
                        newValue
                    }
                },
                textStyle = MaterialTheme.typography.bodyMedium,
                colors = TextFieldDefaults.colors().copy(
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                ),
            )
        },
    )
}

@Preview
@Composable
private fun PiggyBankAssetDetailItemPreview() {
    PreviewTheme {
        PiggyBankAssetDetailItem(
            field = "Asset Name",
            content = "PersonKonto",
            readOnly = false,
        )
    }
}
