package com.picpay.framework.di

import com.picpay.presentation.main.MainViewModel
import dagger.Component

@Component(
    modules = [
        ApplicationModule::class,
        RepositoryModule::class,
        UseCasesModule::class
    ]
)
interface ViewModelComponent {
    fun inject(mainViewModel: MainViewModel)
}