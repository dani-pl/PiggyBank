package com.danipl.piggybank.android

import com.danipl.piggybank.android.data.AssetsLocalDataSource
import com.danipl.piggybank.android.data.DefaultAssetsLocalDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [AppModule.Binder::class])
@InstallIn(SingletonComponent::class)
class AppModule {

    @Module
    @InstallIn(SingletonComponent::class)
    interface Binder {
        @Binds
        fun bindAssetsLocalDataSource(
            assetsLocalDataSource: DefaultAssetsLocalDataSource,
        ): AssetsLocalDataSource
    }
}
