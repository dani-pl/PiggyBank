package com.danipl.piggybank.android.domain

data class Asset(
    val assetId: Long,
    val name: String,
    val amount: String,
    val registeredOn: Long,
    val imgDrawableRes: Int,
)
