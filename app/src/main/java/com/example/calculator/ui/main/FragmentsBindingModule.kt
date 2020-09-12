package com.example.calculator.ui.main

import com.example.calculator.ui.authentication.LoginFragment
import com.example.calculator.ui.calculation.calculatordashboard
import com.example.calculator.ui.history.History
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class FragmentsBindingModule {

    @ContributesAndroidInjector
    abstract fun provideLoginFragment(): LoginFragment?

    @ContributesAndroidInjector
    abstract fun provideHistoryFragment(): History?

    @ContributesAndroidInjector
    abstract fun provideCalculatorDashboardFragment(): calculatordashboard?
}