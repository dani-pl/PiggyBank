package com.danipl.piggybank.android.editAsset

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danipl.piggybank.android.domain.usecases.SaveAssetUseCase
import com.danipl.piggybank.android.domain.usecases.validation.ValidateAmountUseCase
import com.danipl.piggybank.android.domain.usecases.validation.ValidateAssetNameUseCase
import com.danipl.piggybank.android.domain.usecases.validation.ValidationResult
import com.danipl.piggybank.android.util.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import javax.inject.Inject

@OptIn(ExperimentalMaterial3Api::class)
@HiltViewModel
class EditAssetViewModel @Inject constructor(
    private val saveAssetUseCase: SaveAssetUseCase,
    private val validateAmountUseCase: ValidateAmountUseCase,
    private val validateAssetNameUseCase: ValidateAssetNameUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(
        EditAssetState(
            assetName = "My bank account",
            assetNameError = null,
            amount = "0.00",
            amountError = null,
            registeredOn = Instant.now().toEpochMilli(),
            selectableDates = selectableDates,
            imgRes = com.danipl.piggybank.R.drawable.nordea,
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
                AssetFieldType.NAME -> it.copy(
                    assetName = content,
                    assetNameError = null,
                )
                AssetFieldType.AMOUNT -> it.copy(
                    amount = content,
                    amountError = null,
                )
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

    fun saveAsset(): Boolean {
        if (isAnyFieldInvalid()) return false

        viewModelScope.launch {
            saveAssetUseCase.invoke(
                assetName = state.value.assetName,
                amount = state.value.amount,
                registeredOn = state.value.registeredOn,
            )
        }
        return true
    }

    private fun isAnyFieldInvalid(): Boolean {
        clearErrorMessages()

        val assetNameResult = validateAssetNameUseCase(state.value.assetName)
        val amountResult = validateAmountUseCase(state.value.amount)

        when {
            assetNameResult is ValidationResult.Error -> {
                _state.update {
                    it.copy(
                        assetNameError = assetNameResult.errorMgs,
                    )
                }
                return true
            }

            amountResult is ValidationResult.Error -> {
                _state.update {
                    it.copy(
                        amountError = amountResult.errorMgs,
                    )
                }
                return true
            }
        }
        return false
    }

    private fun clearErrorMessages() {
        _state.update {
            it.copy(
                amountError = null,
                assetNameError = null,
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

data class EditAssetState
@OptIn(ExperimentalMaterial3Api::class)
constructor(
    val assetName: String,
    val assetNameError: UiText?,
    val amount: String,
    val amountError: UiText?,
    val registeredOn: Long,
    val selectableDates: SelectableDates,
    val imgRes: Int?,
    val openDatePickerDialog: Boolean,
)

@OptIn(ExperimentalMaterial3Api::class)
private val selectableDates = object : SelectableDates {
    override fun isSelectableDate(utcTimeMillis: Long): Boolean {
        val selectedLocalDate =
            Instant.ofEpochMilli(utcTimeMillis).atZone(ZoneId.of("UTC")).toLocalDate()
        return selectedLocalDate.isBefore(LocalDate.now()) || selectedLocalDate.isEqual(LocalDate.now())
    }
}
