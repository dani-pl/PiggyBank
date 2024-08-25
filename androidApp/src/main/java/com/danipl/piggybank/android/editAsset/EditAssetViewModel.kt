package com.danipl.piggybank.android.editAsset

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danipl.piggybank.android.domain.Asset
import com.danipl.piggybank.android.domain.usecases.SaveAssetUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.Instant
import javax.inject.Inject

@HiltViewModel
class EditAssetViewModel @Inject constructor(
    private val saveAssetUseCase: SaveAssetUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(
        EditAssetState(
            assetName = "My bank account",
            amount = "0,00",
            registeredOn = Instant.now().toEpochMilli(),
            imgRes = null,
            openDatePickerDialog = false,
        ),
    )

    val state = _state.asStateFlow()

    fun updateAsset(
        content: String,
        type: AssetFieldType,
    ) {
        _state.update {
            when (type) {
                AssetFieldType.NAME -> it.copy(assetName = content)
                AssetFieldType.AMOUNT -> it.copy(amount = content)
                AssetFieldType.REGISTERED_ON -> it // Updated by updateDate() instead
                AssetFieldType.IMG -> it.copy(imgRes = null)
            }
        }
    }

    fun updateDate(
        date: Long?,
    ) {
        if (date == null) return
        _state.update {
            it.copy(
                registeredOn = date,
            )
        }
    }

    fun saveAsset() {
        viewModelScope.launch {
            saveAssetUseCase.invoke(
                Asset(
                    name = state.value.assetName,
                    amount = state.value.amount,
                    registeredOn = state.value.registeredOn,
                    imgDrawableRes = 1,
                ),
            )
        }
    }

    fun changeDatePickerDialogVisibility(visible: Boolean) {
        _state.update {
            it.copy(
                openDatePickerDialog = visible,
            )
        }
    }
}

data class EditAssetState(
    val assetName: String,
    val amount: String,
    val registeredOn: Long,
    val imgRes: ImageVector?,
    val openDatePickerDialog: Boolean,
)
