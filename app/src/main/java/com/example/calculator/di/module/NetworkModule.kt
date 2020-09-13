package com.example.calculator.di.module

import com.example.calculator.data.NetworkService
import com.example.calculator.data.NetworkServiceRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ViewModelFactoryModule::class])
class NetworkModule {

    @Singleton
    @Provides
    fun provideNetworkServiceRepository(networkService: NetworkService) = NetworkServiceRepository(networkService)

    @Singleton
    @Provides
    fun provideNetworkService() = NetworkService()
}

