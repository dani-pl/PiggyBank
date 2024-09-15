package com.danipl.piggybank.android.data

import com.danipl.piggybank.android.domain.Asset
import com.danipl.piggybank.android.domain.toAssetDb
import com.danipl.piggybank.android.domain.toListOfAssets
import javax.inject.Inject

class DefaultAssetsRepository @Inject constructor(
    private val assetsLocalDataSource: AssetsLocalDataSource,
) : AssetsRepository {

    override fun saveAsset(asset: Asset) {
        assetsLocalDataSource.saveAsset(asset.toAssetDb())
    }

    override fun removeAsset(asset: Asset) {
        assetsLocalDataSource.removeAsset(asset.toAssetDb())
    }

    override fun getAllAssets() = assetsLocalDataSource.getAllAssets().toListOfAssets()
}
