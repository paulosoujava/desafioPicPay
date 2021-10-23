package com.picpay.framework.di

import com.picpay.core.repository.UserRepository
import com.picpay.core.usecase.AddUser
import com.picpay.core.usecase.GetAllUser
import com.picpay.framework.UseCases
import dagger.Module
import dagger.Provides

@Module
class UseCasesModule {
    @Provides
    fun getUseCases(repository: UserRepository)= UseCases(
        AddUser(repository),
        GetAllUser(repository)
    )
}