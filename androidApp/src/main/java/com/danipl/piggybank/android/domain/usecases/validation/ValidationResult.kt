package com.danipl.piggybank.android.domain.usecases.validation

import com.danipl.piggybank.android.util.UiText

sealed class ValidationResult {
    data object Success : ValidationResult()
    data class Error(val errorMgs: UiText.StringResource) : ValidationResult()
}
