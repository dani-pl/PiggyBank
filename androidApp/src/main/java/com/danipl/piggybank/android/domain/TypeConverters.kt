package com.danipl.piggybank.android.domain

import com.danipl.piggybank.android.data.db.AssetDb
import com.danipl.piggybank.android.util.toLong
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
fun Asset.toAssetDb(): AssetDb =
    AssetDb(
        assetId = Uuid.random().toLong(),
        name = name,
        amount = amount.toDouble(),
        registeredOn = registeredOn,
        imgDrawableRes = imgDrawableRes,
    )

fun AssetDb.toAsset(): Asset =
    Asset(
        assetId = assetId,
        name = name,
        amount = amount.toString(),
        registeredOn = registeredOn,
        imgDrawableRes = imgDrawableRes,
    )

fun List<AssetDb>.toListOfAssets(): List<Asset> =
    this.map {
        it.toAsset()
    }
