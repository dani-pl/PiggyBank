package com.danipl.piggybank.android.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AssetDao {
    @Query("SELECT * FROM assetDb")
    fun getAll(): List<AssetDb>

    @Query("SELECT * FROM assetDb WHERE assetId IN (:assetIds)")
    fun loadAllByIds(assetIds: IntArray): List<AssetDb>

    @Query("SELECT * FROM assetDb WHERE name LIKE :assetName LIMIT 1")
    fun findByName(assetName: String): AssetDb

    @Insert
    fun insertAll(vararg assets: AssetDb)

    @Delete
    fun delete(asset: AssetDb)
}
