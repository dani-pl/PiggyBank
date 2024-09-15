package com.danipl.piggybank.android.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AssetDb(
    @PrimaryKey val assetId: Long,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "amount") val amount: Double,
    @ColumnInfo(name = "registeredOn") val registeredOn: Long,
    @ColumnInfo(name = "imgDrawableRes") val imgDrawableRes: Int,
)
