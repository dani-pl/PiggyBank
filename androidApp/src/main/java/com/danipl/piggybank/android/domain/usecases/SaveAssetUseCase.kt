package com.danipl.piggybank.android.domain.usecases

import com.danipl.piggybank.android.data.AssetsRepository
import com.danipl.piggybank.android.domain.Asset
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SaveAssetUseCase @Inject constructor(
    private val coroutineDispatcher: CoroutineDispatcher,
    private val assetsRepository: AssetsRepository,
) {
    suspend operator fun invoke(asset: Asset) = withContext(coroutineDispatcher) {
        assetsRepository.saveAsset()
    }
}
