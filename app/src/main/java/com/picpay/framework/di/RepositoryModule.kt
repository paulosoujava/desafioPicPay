package com.picpay.framework.di

import android.app.Application
import com.picpay.core.repository.UserRepository
import com.picpay.framework.db.RoomDataSource
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {
    @Provides
    fun provideRepository(app: Application) = UserRepository(RoomDataSource(app))
}