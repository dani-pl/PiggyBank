package com.danipl.piggybank.android.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.danipl.piggybank.android.components.datepicker.PiggyBankDatePicker
import com.danipl.piggybank.android.editAsset.AssetFieldType
import com.danipl.piggybank.android.util.getYyyyMmDdDate

@Composable
fun PiggyBankTextField(
    readOnly: Boolean = true,
    value: String,
    date: Long,
    fieldName: String,
    fieldType: AssetFieldType,
    useDatePicker: Boolean = false,
    openDialog: Boolean = false,
    changeDatePickerDialogVisibility: (Boolean) -> Unit,
    updateAsset: (String, AssetFieldType) -> Unit,
    updateDate: (Long?) -> Unit,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
) {
    TextField(
        label = { Text(text = fieldName) },
        // modifier = Modifier.clickable { if(useDatePicker) changeDatePickerDialogVisibility(true) },
        readOnly = readOnly,
        value = if (fieldType == AssetFieldType.REGISTERED_ON) date.getYyyyMmDdDate() else value,
        onValueChange = { newValue ->
            updateAsset(newValue, fieldType)
        },
        shape = RoundedCornerShape(25.dp),
        textStyle = MaterialTheme.typography.bodyMedium,
        colors = TextFieldDefaults.colors().copy(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        interactionSource = remember { MutableInteractionSource() }
            .also { interactionSource ->
                LaunchedEffect(interactionSource) {
                    interactionSource.interactions.collect {
                        if (it is PressInteraction.Release) {
                            if (useDatePicker) changeDatePickerDialogVisibility(true)
                        }
                    }
                }
            },
        keyboardOptions = keyboardOptions,
    )

    if (useDatePicker && openDialog) {
        PiggyBankDatePicker(
            changeDatePickerDialogVisibility = changeDatePickerDialogVisibility,
            updateDate = updateDate,
            registeredOn = date,
        )
    }
}
