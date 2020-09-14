package com.example.calculator.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.calculator.di.util.ViewModelKey
import com.example.calculator.ui.authentication.LoginViewModel
import com.example.calculator.ui.calculation.CalculatorViewModel
import com.example.calculator.ui.factory.ViewModelFactory
import com.example.calculator.ui.history.HistoryViewModel

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [ContextModule::class])
abstract class ViewModelFactoryModule {


    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    internal abstract fun bindLoginViewModel(loginViewModel: LoginViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CalculatorViewModel::class)
    internal abstract fun bindCalculatordashboardViewModel(calculatorViewModel: CalculatorViewModel) : ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(HistoryViewModel::class)
    internal abstract fun bindHistoryViewModel(historyViewModel: HistoryViewModel) :ViewModel


    @Binds
    internal abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory) : ViewModelProvider.Factory
}