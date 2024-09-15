package com.danipl.piggybank.android.data

import com.danipl.piggybank.android.domain.Asset

interface AssetsRepository {
    fun saveAsset(asset: Asset)
    fun removeAsset(asset: Asset)
    fun getAllAssets(): List<Asset>
}
