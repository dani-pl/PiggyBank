package com.danipl.piggybank.android.components.datepicker

import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.window.DialogProperties

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PiggyBankDatePicker(
    changeDatePickerDialogVisibility: (Boolean) -> Unit,
    updateDate: (Long?) -> Unit,
    registeredOn: Long,
    selectableDates: SelectableDates,
) {
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = registeredOn,
        selectableDates = selectableDates,
    )
    val confirmEnabled = remember {
        derivedStateOf { datePickerState.selectedDateMillis != null }
    }

    DatePickerDialog(
        onDismissRequest = {
            // Dismiss the dialog when the user clicks outside the dialog or on the back
            // button
            changeDatePickerDialogVisibility(false)
        },
        confirmButton = {
            TextButton(
                onClick = {
                    changeDatePickerDialogVisibility(false)
                    updateDate(datePickerState.selectedDateMillis)
                },
                enabled = confirmEnabled.value,
            ) {
                Text("OK")
            }
        },
        dismissButton = {
            TextButton(onClick = { changeDatePickerDialogVisibility(false) }) { Text("Cancel") }
        },
        properties = DialogProperties(),
    ) {
        DatePicker(state = datePickerState)
    }
}
