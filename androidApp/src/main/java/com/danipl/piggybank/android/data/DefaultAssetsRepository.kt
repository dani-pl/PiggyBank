package com.danipl.piggybank.android.data

import javax.inject.Inject

class DefaultAssetsRepository @Inject constructor(
    private val assetsLocalDataSource: AssetsLocalDataSource,
) : AssetsRepository {

    override fun saveAsset() {
        assetsLocalDataSource.saveAsset()
    }
}
