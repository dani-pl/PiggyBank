package com.danipl.piggybank.android.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [AssetDb::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun assetDao(): AssetDao
}
