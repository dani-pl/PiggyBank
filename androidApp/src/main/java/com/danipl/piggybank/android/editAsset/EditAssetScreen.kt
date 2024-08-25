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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.danipl.piggybank.android.components.PiggyBankTextField
import com.danipl.piggybank.android.util.PreviewTheme

@Composable
fun EditAssetRoute(
    onNavigateBack: () -> Unit,
    editAssetViewModel: EditAssetViewModel = hiltViewModel(),
) {
    val state: EditAssetState by editAssetViewModel.state.collectAsState()

    EditAssetScreen(
        onNavigateBack = onNavigateBack,
        onSaveAssetClicked = editAssetViewModel::saveAsset,
        updateAsset = { content: String, type: AssetFieldType ->
            editAssetViewModel.updateAsset(content, type)
        },
        updateDate = { date: Long? ->
            editAssetViewModel.updateDate(date)
        },
        changeDatePickerDialogVisibility = { visible: Boolean ->
            editAssetViewModel.changeDatePickerDialogVisibility(visible)
        },
        state = state,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditAssetScreen(
    onNavigateBack: () -> Unit,
    onSaveAssetClicked: () -> Unit,
    updateAsset: (String, AssetFieldType) -> Unit,
    updateDate: (Long?) -> Unit,
    changeDatePickerDialogVisibility: (Boolean) -> Unit,
    state: EditAssetState,
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
                        IconButton(onClick = onNavigateBack) {
                            Icon(
                                painter = painterResource(id = com.danipl.piggybank.R.drawable.ic_close),
                                contentDescription = "",
                            )
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        IconButton(onClick = onSaveAssetClicked) {
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
            updateAsset = updateAsset,
            updateDate = updateDate,
            changeDatePickerDialogVisibility = changeDatePickerDialogVisibility,
            state = state,
        )
    }
}

@Composable
fun EditAssetContent(
    innerPadding: PaddingValues,
    updateAsset: (String, AssetFieldType) -> Unit,
    updateDate: (Long?) -> Unit,
    changeDatePickerDialogVisibility: (Boolean) -> Unit,
    state: EditAssetState,
) {
    Column(
        modifier = Modifier.padding(innerPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
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

        PiggyBankTextField(
            readOnly = false,
            value = state.assetName,
            date = 1L,
            fieldName = stringResource(id = com.danipl.piggybank.R.string.asset_name),
            fieldType = AssetFieldType.NAME,
            updateAsset = updateAsset,
            updateDate = updateDate,
            changeDatePickerDialogVisibility = changeDatePickerDialogVisibility,
        )

        Spacer(modifier = Modifier.height(16.dp))

        PiggyBankTextField(
            readOnly = false,
            value = state.amount,
            date = 1L,
            fieldName = stringResource(id = com.danipl.piggybank.R.string.amount),
            fieldType = AssetFieldType.AMOUNT,
            updateAsset = updateAsset,
            updateDate = updateDate,
            changeDatePickerDialogVisibility = changeDatePickerDialogVisibility,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        )

        Spacer(modifier = Modifier.height(16.dp))

        PiggyBankTextField(
            readOnly = true,
            value = "",
            date = state.registeredOn,
            fieldName = stringResource(id = com.danipl.piggybank.R.string.registered_on),
            fieldType = AssetFieldType.REGISTERED_ON,
            updateAsset = updateAsset,
            updateDate = updateDate,
            useDatePicker = true,
            openDialog = state.openDatePickerDialog,
            changeDatePickerDialogVisibility = changeDatePickerDialogVisibility,
        )
    }
}

@Preview
@Composable
private fun PreviewEditAssetScreen() {
    PreviewTheme {
        EditAssetScreen(
            onNavigateBack = {},
            onSaveAssetClicked = {},
            updateAsset = { _, _ -> },
            updateDate = { _ -> },
            changeDatePickerDialogVisibility = {},
            state = EditAssetState(
                assetName = "Personkonto",
                amount = "1000.00",
                registeredOn = "".toLong(),
                imgRes = null,
                openDatePickerDialog = false,
            ),
        )
    }
}

enum class AssetFieldType {
    NAME, AMOUNT, REGISTERED_ON, IMG
}
