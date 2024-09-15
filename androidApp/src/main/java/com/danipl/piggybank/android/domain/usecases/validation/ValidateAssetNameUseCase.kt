package com.danipl.piggybank.android.domain.usecases.validation

import com.danipl.piggybank.android.util.UiText
import javax.inject.Inject

class ValidateAssetNameUseCase @Inject constructor() {

    operator fun invoke(assetName: String): ValidationResult {
        val errorMsg: Int? = when {
            assetName.isEmpty() -> com.danipl.piggybank.R.string.validation_asset_name_too_short
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
