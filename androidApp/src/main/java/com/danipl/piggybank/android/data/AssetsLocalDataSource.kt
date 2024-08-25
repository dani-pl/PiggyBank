package com.danipl.piggybank.android.data

import javax.inject.Inject

interface AssetsLocalDataSource {
    fun saveAsset()
}

class DefaultAssetsLocalDataSource @Inject constructor() : AssetsLocalDataSource {

    override fun saveAsset() {
    }
}
