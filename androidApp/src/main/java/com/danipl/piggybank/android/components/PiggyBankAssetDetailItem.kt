package com.danipl.piggybank.android.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.danipl.piggybank.android.editAsset.AssetFieldType
import com.danipl.piggybank.android.util.PreviewTheme

@Composable
fun PiggyBankAssetDetailItem(
    fieldName: String,
    fieldType: AssetFieldType,
    content: String,
    readOnly: Boolean,
    updateAsset: (String, AssetFieldType) -> Unit,
) {
    ListItem(
        headlineContent = {},
        leadingContent = {
            Row(
                verticalAlignment = Alignment.Bottom,
            ) {
                Text(fieldName)
            }
        },
        trailingContent = {
            TextField(
                readOnly = readOnly,
                value = content,
                onValueChange = { newValue ->
                    updateAsset(newValue, fieldType)
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
            fieldName = "Asset Name",
            fieldType = AssetFieldType.NAME,
            content = "PersonKonto",
            readOnly = false,
            updateAsset = { _, _ -> },
        )
    }
}
