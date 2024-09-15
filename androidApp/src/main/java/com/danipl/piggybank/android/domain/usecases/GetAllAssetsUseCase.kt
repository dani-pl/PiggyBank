package com.danipl.piggybank.android.domain.usecases

import com.danipl.piggybank.android.data.AssetsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetAllAssetsUseCase @Inject constructor(
    private val coroutineDispatcher: CoroutineDispatcher,
    private val assetsRepository: AssetsRepository,
) {
    suspend operator fun invoke() = withContext(coroutineDispatcher) {
        assetsRepository.getAllAssets()
    }
}
