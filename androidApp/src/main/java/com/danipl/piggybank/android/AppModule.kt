package com.danipl.piggybank.android

import android.content.Context
import androidx.room.Room
import com.danipl.piggybank.android.data.AssetsLocalDataSource
import com.danipl.piggybank.android.data.AssetsRepository
import com.danipl.piggybank.android.data.DefaultAssetsLocalDataSource
import com.danipl.piggybank.android.data.DefaultAssetsRepository
import com.danipl.piggybank.android.data.db.AppDatabase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module(includes = [AppModule.Binder::class])
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    fun provideRoomDb(
        @ApplicationContext
        applicationContext: Context,
    ) = Room.databaseBuilder(
        applicationContext,
        AppDatabase::class.java,
        "database-name",
    ).build()

    @Module
    @InstallIn(SingletonComponent::class)
    interface Binder {

        @Binds
        fun bindAssetsRepository(
            assetsRepository: DefaultAssetsRepository,
        ): AssetsRepository

        @Binds
        fun bindAssetsLocalDataSource(
            assetsLocalDataSource: DefaultAssetsLocalDataSource,
        ): AssetsLocalDataSource
    }
}
