package com.danipl.piggybank.android.domain.usecases.validation

import com.danipl.piggybank.android.util.UiText
import javax.inject.Inject

class ValidateAmountUseCase @Inject constructor() {

    operator fun invoke(amount: String): ValidationResult {
        val errorMsg: Int? = when {
            !Regex("^[0-9.]+\$").matches(amount) -> com.danipl.piggybank.R.string.validation_amount_no_valid_number
            amount.contains(",") -> com.danipl.piggybank.R.string.validation_amount_no_comma
            amount.count { it == '.' } > 1 -> com.danipl.piggybank.R.string.validation_amount_only_one_point
            amount.toDoubleOrNull() == null -> com.danipl.piggybank.R.string.validation_amount_unknown
            else -> null
        }

        if (errorMsg != null) {
            return ValidationResult.Error(
                errorMgs = UiText.StringResource(errorMsg),
            )
        }

        return ValidationResult.Success
    }
}
