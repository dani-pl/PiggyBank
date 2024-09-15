package com.danipl.piggybank.android.domain.usecases

import com.danipl.piggybank.android.data.AssetsRepository
import com.danipl.piggybank.android.data.DatabaseResult
import com.danipl.piggybank.android.domain.Asset
import com.danipl.piggybank.android.util.toLong
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

class SaveAssetUseCase @Inject constructor(
    private val coroutineDispatcher: CoroutineDispatcher,
    private val assetsRepository: AssetsRepository,
) {
    @OptIn(ExperimentalUuidApi::class)
    suspend operator fun invoke(
        assetName: String,
        amount: String,
        registeredOn: Long,
    ): DatabaseResult<Unit> = withContext(coroutineDispatcher) {
        assetsRepository.saveAsset(
            Asset(
                assetId = Uuid.random().toLong(),
                name = assetName,
                amount = amount,
                registeredOn = registeredOn,
                imgDrawableRes = com.danipl.piggybank.R.drawable.nordea,
            ),
        )
        DatabaseResult.Success(Unit)
    }
}

data class EditAssetState(
    val assetName: String,
    val amount: String,
    val registeredOn: Long,
    val imgRes: Int?,
    val openDatePickerDialog: Boolean,
)
