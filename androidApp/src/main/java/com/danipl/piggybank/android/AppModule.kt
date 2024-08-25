package com.danipl.piggybank.android

import com.danipl.piggybank.android.data.AssetsLocalDataSource
import com.danipl.piggybank.android.data.AssetsRepository
import com.danipl.piggybank.android.data.DefaultAssetsLocalDataSource
import com.danipl.piggybank.android.data.DefaultAssetsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module(includes = [AppModule.Binder::class])
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO

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
