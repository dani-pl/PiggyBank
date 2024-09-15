package com.danipl.piggybank.android.data

import com.danipl.piggybank.android.data.db.AppDatabase
import com.danipl.piggybank.android.data.db.AssetDb
import javax.inject.Inject

interface AssetsLocalDataSource {
    fun saveAsset(assetDb: AssetDb)
    fun removeAsset(assetDb: AssetDb)
    fun getAllAssets(): List<AssetDb>
}

class DefaultAssetsLocalDataSource @Inject constructor(
    appDatabase: AppDatabase,
) : AssetsLocalDataSource {

    private val assetDao = appDatabase.assetDao()

    override fun saveAsset(assetDb: AssetDb) {
        assetDao.insertAll(assetDb)
    }

    override fun removeAsset(assetDb: AssetDb) {
        assetDao.delete(assetDb)
    }

    override fun getAllAssets(): List<AssetDb> = assetDao.getAll()
}
