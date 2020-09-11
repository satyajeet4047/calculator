package com.example.calculator.ui

import com.example.calculator.ui.authentication.login.Login
import com.example.calculator.ui.authentication.signup.SignUp
import com.example.calculator.ui.calculatorfragment.CalculatordashboardViewModel
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class FragmentsBindingModule {

    @ContributesAndroidInjector
    abstract fun provideLoginFragment(): Login?

    @ContributesAndroidInjector
    abstract fun provideSignUpFragment(): SignUp?

    @ContributesAndroidInjector
    abstract fun provideCalculatorDashboardFragment(): CalculatordashboardViewModel?
}